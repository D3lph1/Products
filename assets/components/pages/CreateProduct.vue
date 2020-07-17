<template>
    <div>
        <b-form>
            <b-form-group>
                <b-form-input v-model="name" placeholder="Name"></b-form-input>
            </b-form-group>
            <b-form-group>
                <b-form-input v-model="article" placeholder="Article"></b-form-input>
            </b-form-group>
            <b-form-group>
                <b-form-input v-model="barcode" placeholder="Barcode"></b-form-input>
            </b-form-group>
            <b-form-group label="Select brand:" label-for="brand-input">
                <b-form-select id="brand-input" v-model="brand" :options="brands"></b-form-select>
            </b-form-group>
        </b-form>

        <list
                :fields="fields"
                :items="offers"
                disable-search
                disable-pagination
                event-on-deletion
                @delete="deleteItems"
                fixed
                class="mt-4"
        >
            <template v-slot:tools>
                <b-btn size="sm" variant="outline-primary" class="mb-3" @click="offerModal = !offerModal">
                    <b-icon icon="plus" aria-hidden="true"></b-icon>
                    Add offer
                </b-btn>
            </template>

            <template v-slot:cell(actualUntil)="data">
                <span :class="data.item.actualUntil < new Date() ? 'text-danger' : ''">
                    {{ formatDate(data.item.actualUntil) }}
                </span>
            </template>

            <template v-slot:cell(actions)="data">
                <b-btn size="sm" variant="outline-danger" @click="deleteItems([data.item])">
                    <b-icon icon="trash" aria-hidden="true"></b-icon>
                </b-btn>
            </template>
        </list>

        <button-loading class="mt-3" variant="primary" :loading="loading" @click="submit">Create product</button-loading>

        <b-modal v-model="offerModal" title="Add offer">
            <b-form>
                <b-form-group label="Set offer price:" label-for="price">
                    <b-form-input id="price" type="number" min="0" placeholder="price" v-model="price"></b-form-input>
                </b-form-group>

                <b-form-group label="Set offer expiration date:" label-for="date-picker">
                    <b-form-datepicker id="date-picker" v-model="date" class="mb-2"></b-form-datepicker>
                </b-form-group>

                <b-form-group label="Set offer expiration time:" label-for="time-picker">
                    <b-form-group class="text-center">
                        <b-time id="time-picker" v-model="time" locale="ru"></b-time>
                    </b-form-group>
                </b-form-group>
            </b-form>

            <template v-slot:modal-footer="{ ok, cancel }">
                <button-loading  variant="success" :loading="validateOfferLoading" @click="validateOffer">
                    OK
                </button-loading>
                <b-button @click="cancel()">
                    Cancel
                </b-button>
            </template>
        </b-modal>
    </div>
</template>

<script lang="ts">
    import '~/hooks'
    import {Component, Vue} from 'vue-property-decorator'
    import axios, {ApiResponse, Brand, NewOffer, TableItem} from "~/http";
    import List from "@/common/List.vue";
    import {NavigationGuardNext, Route} from "vue-router";
    import {AxiosResponse} from "axios";
    import ButtonLoading from "@/common/ButtonLoading.vue";
    import {BvTableFieldArray} from "bootstrap-vue/src/components/table";
    import {formatDate} from '~/util'
    import {BvTimeCtxEvent} from "bootstrap-vue";

    interface NewOfferTableItem extends NewOffer, TableItem {
    }

    interface BrandsApiResponse extends ApiResponse {
        brands: Brand[]
    }

    @Component({
        components: {
            'button-loading': ButtonLoading,
            'list': List
        }
    })
    export default class CreateProduct extends Vue {
        private name: string = '';

        private article: string = '';

        private barcode: string = '';

        private brand: number | null = null;

        private brands: any[] = [];

        private loading: boolean = false;

        private validateOfferLoading: boolean = false;

        private fields: BvTableFieldArray = [
            {
                key: 'price',
                sortable: true
            },
            {
                key: 'actualUntil',
                sortable: true
            },
            {
                key: 'actions',
                sortable: false
            }
        ];

        private offers: NewOfferTableItem[] = [];

        private idCounter: number = 0;

        private offerModal: boolean = false;

        private price: number = 0;

        private date: string = '';

        private time: string = '';

        private beforeRouteEnter(from: Route, to: Route, next: NavigationGuardNext<CreateProduct>) {
            next(vm => axios.get('/api/brands/all')
                .then((response: AxiosResponse<BrandsApiResponse>) => {
                    vm.brands = response.data.brands.map((brand: Brand) => ({value: brand.id, text: brand.name}))
                })
            )
        }

        private submit() {
            this.loading = true;

            axios.post('/api/products', {
                name: this.name,
                article: this.article,
                barcode: this.barcode,
                brand: this.brand,
                offers: this.offers.map(offer => ({
                    price: offer.price,
                    actualUntil: offer.actualUntil.getTime()
                }))
            })
                .then((response: AxiosResponse<ApiResponse>) => {
                    if (response.data.ok) {
                        this.$router.push({name: 'products.list'});
                    } else {
                        this.loading = false;
                    }
                })
                .catch(() => this.loading = false);
        }

        private deleteItems(items: NewOfferTableItem[]): void {
            for (const item of items) {
                this.offers.splice(this.offers.map(offer => offer.id).indexOf(item.id), 1);
            }
        }

        private validateOffer(): void {
            this.validateOfferLoading = true;

            const offer: NewOfferTableItem = {
                id: this.idCounter++,
                price: this.price,
                actualUntil: new Date(this.time + ' ' + this.date),
                isActive: false,
                delete: null
            }

            axios.post('/api/offers/validate', {price: offer.price, actualUntil: offer.actualUntil.getTime()})
                .then((response: AxiosResponse<ApiResponse>) => {
                    if (response.data.ok) {
                        this.offers.push(offer);
                        this.offerModal = false;
                    }
                })
                .finally(() => this.validateOfferLoading = false);
        }

        private formatDate(date: Date): string {
            return formatDate(date);
        }
    }
</script>
