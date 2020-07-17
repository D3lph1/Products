<template>
    <list
            searchPlaceholder="Search by product name, article or brand name..."
            :fields="fields"
            load-url="/api/products"
            delete-url="/api/products"
            :edit-route="updateRoute"
            :create-route="{name: 'products.create'}"
            :items-extractor="itemsExtractor"
            :pages-extractor="pagesExtractor"
            fixed
    >
        <template v-slot:cell(brand)="data">
            <router-link :to="{name: 'brands.update', params: {brand: data.item.brand.id}}">{{ data.item.brand.name }}</router-link>
        </template>
    </list>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import {ApiResponse, PaginationResponse, Product, TableItem} from "~/http";
    import List from "@/common/List.vue";
    import {BvTableFieldArray} from "bootstrap-vue/src/components/table";

    interface ProductItem extends Product, TableItem {
    }

    interface ProductsApiResponse extends ApiResponse {
        products: PaginationResponse<Product>
    }

    @Component({
        components: {
            'list': List
        }
    })
    export default class Products extends Vue {
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
                key: 'article',
                sortable: true
            },
            {
                key: 'barcode',
                sortable: true
            },
            {
                key: 'brand',
                sortable: true
            },
            {
                key: 'actions',
                sortable: false
            }
        ];

        private itemsExtractor(response: ProductsApiResponse): ProductItem[] {
            const items: ProductItem[] = [];
            for (const product of response.products.items) {
                items.push(<ProductItem>product)
            }

            return items;
        }

        private pagesExtractor(response: ProductsApiResponse): number {
            return response.products.pages;
        }

        private updateRoute(item: TableItem): any {
            return {name: 'products.update', params: {product: item.id}};
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
