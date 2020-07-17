<template>
    <div>
        <b-input-group class="mb-3" v-if="!disableSearch">
            <b-input-group-prepend is-text>
                <b-icon icon="search"/>
            </b-input-group-prepend>

            <b-form-input v-model="filter" debounce="300" :placeholder="searchPlaceholder"></b-form-input>
            <b-input-group-append>
                <b-button variant="outline-success" :to="createRoute">Create</b-button>
            </b-input-group-append>
        </b-input-group>
        <slot name="tools"></slot>
        <b-btn
                size="sm"
                variant="outline-danger"
                class="mb-3"
                :disabled="selected.length === 0"
                @click="eventOnDeletion ? $emit('delete', selected) : deleteItems(selected)"
                v-b-tooltip.hover
                title="You can select few items and process multiple delete"
        >
            <b-icon icon="trash" aria-hidden="true"></b-icon>
            Delete
        </b-btn>
        <b-table
                v-bind="$attrs"
                id="table"
                ref="table"
                selectable
                select-mode="multi"
                :items="items !== null ? items : load"
                :fields="fields"
                :filter="filter"
                :current-page="page"
                :per-page="disablePagination ? 0 : perPage"
                @row-selected="onRowSelected"
                responsive="sm"
                :sort-by.sync="sortBy"
                :sort-desc.sync="sortDesc"
        >
            <template v-slot:cell(actions)="data">
                <b-btn size="sm" variant="outline-info" v-if="editRoute !== null" :to="editRoute !== null ? editRoute(data.item) : null">
                    <b-icon icon="pencil" aria-hidden="true"></b-icon>
                </b-btn>
                <b-btn size="sm" variant="outline-danger" @click="deleteItems([data.item])">
                    <b-icon icon="trash" aria-hidden="true"></b-icon>
                </b-btn>
            </template>

            <template v-slot:table-busy>
                <div class="text-center text-primary my-2">
                    <b-spinner class="align-middle"></b-spinner>
                    <strong>Loading...</strong>
                </div>
            </template>

            <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope"><slot :name="slot" v-bind="scope"/></template>
        </b-table>

        <div class="pagination" v-if="!disablePagination">
            <b-pagination
                    v-model="page"
                    :total-rows="total"
                    :per-page="perPage"
                    aria-controls="table"
            ></b-pagination>
        </div>
    </div>
</template>

<script lang="ts">
    import qs from 'qs'
    import {Component, Vue, Prop} from 'vue-property-decorator'
    import {BvTableFieldArray} from "bootstrap-vue/src/components/table";
    import {BvTableCtxObject} from "bootstrap-vue";
    import axios, {ApiResponse, TableItem} from "~/http";
    import {AxiosResponse} from "axios";
    import {Location} from "vue-router";

    @Component
    export default class List<R extends ApiResponse, I extends TableItem> extends Vue {
        @Prop()
        private searchPlaceholder!: string;

        @Prop()
        private fields!: BvTableFieldArray;

        @Prop(String)
        private loadUrl!: string;

        @Prop(String)
        private deleteUrl!: string;

        @Prop({default: null})
        private createRoute!: string;

        @Prop()
        private itemsExtractor!: (response: R) => I[];

        @Prop()
        private pagesExtractor!: (response: R) => number;

        @Prop({type: Boolean, default: false})
        private disableSearch!: boolean;

        @Prop({type: Boolean, default: false})
        private disablePagination!: boolean;

        @Prop({type: Boolean, default: false})
        private eventOnDeletion!: boolean;

        @Prop({default: null})
        private editRoute!: any;

        @Prop({default: null})
        private items!: I[];

        private filter: string = '';
        private page: number = 1;
        private total: number = 0;
        private perPage: number = 10;
        private selected: I[] = [];
        private sortBy: string = 'id';
        private sortDesc: boolean = false;

        private onRowSelected(items: I[]) {
            this.selected = items;
        }

        private load(ctx: BvTableCtxObject) {
            return axios.get(this.loadUrl, {
                params: {
                    page: ctx.currentPage,
                    perPage: ctx.perPage,
                    sortBy: ctx.sortBy,
                    sortDesc: ctx.sortDesc,
                    filter: ctx.filter
                }
            })
                .then(
                    (response: AxiosResponse<R>) => {
                        const items: I[] = [];

                        for (const item of this.itemsExtractor(response.data)) {
                            item.isActive = false;
                            item.delete = this.deleteItems;
                            items.push(item);
                        }

                        this.total = this.perPage * this.pagesExtractor(response.data);

                        return items;
                    }
                )
        }

        private deleteItems(items: I[]): void {
            if (!confirm('Are you sure?')) {
                return;
            }

            axios.delete(this.deleteUrl, {
                params: {
                    ids: items.map(each => each.id)
                },
                paramsSerializer: (params: any) => qs.stringify(params, {arrayFormat: 'repeat'})
            })
                .then((response: AxiosResponse<ApiResponse>) => {
                    if (response.data.ok) {
                        this.$emit('delete', items)
                    }
                })
                .finally(() => this.$root.$emit('bv::refresh::table', 'table'))
        }
    }
</script>

<style scoped>
    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>
