<template>
    <list
            searchPlaceholder="Search by email..."
            :fields="fields"
            load-url="/api/users"
            delete-url="/api/users"
            :items-extractor="itemsExtractor"
            :pages-extractor="pagesExtractor"
    >
        <template v-slot:cell(actions)="data">
            <b-btn
                    ref="test"
                    size="sm" variant="outline-danger"
                    :disabled="data.item.id === $store.getters.getUser.id"
                    @click="data.item.delete([data.item])"
            >
                <b-icon icon="trash" aria-hidden="true"></b-icon>
            </b-btn>
        </template>
    </list>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import {ApiResponse, PaginationResponse, TableItem, User} from "~/http";
    import List from "@/common/List.vue";
    import {BvTableFieldArray} from "bootstrap-vue/src/components/table";

    interface UserItem extends User, TableItem {
    }

    interface UsersApiResponse extends ApiResponse {
        users: PaginationResponse<User>
    }

    @Component({
        components: {
            'list': List
        }
    })
    export default class Users extends Vue {
        private fields: BvTableFieldArray = [
            {
                key: 'id',
                sortable: true
            },
            {
                key: 'email',
                sortable: true
            },
            {
                key: 'actions',
                sortable: false
            }
        ];

        private itemsExtractor(response: UsersApiResponse): UserItem[] {
            const items: UserItem[] = [];
            for (const product of response.users.items) {
                items.push(<UserItem>product)
            }

            return items;
        }

        private pagesExtractor(response: UsersApiResponse): number {
            return response.users.pages;
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
