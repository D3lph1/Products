<template>
    <b-form>
        <b-form-group>
            <b-form-input v-model="name" placeholder="Name"></b-form-input>
        </b-form-group>
        <b-form-group>
            <button-loading class="mt-3" variant="primary" :loading="loading" @click="submit">Update brand</button-loading>
        </b-form-group>
    </b-form>
</template>

<script lang="ts">
    import '~/hooks'
    import {Component, Vue} from "vue-property-decorator";
    import ButtonLoading from "@/common/ButtonLoading.vue";
    import axios, {ApiResponse, Brand} from "~/http";
    import {AxiosResponse} from "axios";
    import {NavigationGuardNext, Route} from "vue-router";

    interface BrandApiResponse extends ApiResponse {
        brand: Brand
    }

    @Component({
        components: {
            'button-loading': ButtonLoading
        }
    })
    export default class UpdateBrand extends Vue {
        private name: string = '';

        private loading: boolean = false;

        private beforeRouteEnter(from: Route, to: Route, next: NavigationGuardNext<UpdateBrand>) {
            next(vm => axios.get(`/api/brands/${vm.$route.params.brand}`)
                .then((response: AxiosResponse<BrandApiResponse>) => {
                    vm.name = response.data.brand.name
                })
            )
        }

        private submit(): void {
            this.loading = true;

            axios.put(`/api/brands/${this.$route.params.brand}`, {name: this.name})
                .then((response: AxiosResponse<ApiResponse>) => {
                    if (response.data.ok) {
                        this.$router.push({name: 'brands.list'})
                    } else {
                        this.loading = false;
                    }
                })
                .catch(() => this.loading = false)
        }
    }
</script>
