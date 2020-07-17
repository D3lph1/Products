<template>
    <list
            searchPlaceholder="Search by name..."
            :fields="fields"
            :create-route="{name: 'brands.create'}"
            :edit-route="updateRoute"
            load-url="/api/brands"
            delete-url="/api/brands"
            :items-extractor="itemsExtractor"
            :pages-extractor="pagesExtractor"
    >
    </list>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import {ApiResponse, Brand, PaginationResponse, TableItem} from "~/http";
    import List from "@/common/List.vue";
    import {BvTableFieldArray} from "bootstrap-vue/src/components/table";

    interface BrandItem extends Brand, TableItem {
    }

    interface BrandsApiResponse extends ApiResponse {
        brands: PaginationResponse<Brand>
    }

    @Component({
        components: {
            'list': List
        }
    })
    export default class Brands extends Vue {
        private fields: BvTableFieldArray = [
            {
                key: 'id',
                sortable: true
            },
            {
                key: 'name',
                sortable: true
            },
            {
                key: 'actions',
                sortable: false
            }
        ];

        private itemsExtractor(response: BrandsApiResponse): BrandItem[] {
            const items: BrandItem[] = [];
            for (const product of response.brands.items) {
                items.push(<BrandItem>product)
            }

            return items;
        }

        private pagesExtractor(response: BrandsApiResponse): number {
            return response.brands.pages;
        }

        private updateRoute(item: TableItem): any {
            return {name: 'brands.update', params: {brand: item.id}};
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
