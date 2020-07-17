<template>
    <aside class="sidebar">
        <router-link
                class="sidebar__logo"
                tag="div"
                :to="{name: 'index'}"
                title="Go to index page"
                v-b-tooltip.hover.right.window
        >
            <h5>{{ $store.getters.getUser.email }}</h5>
        </router-link>

        <sidebar-collapse
                v-for="{ title, items } in menu"
                :key="title"
                :title="title"
                :items="items"
        />
    </aside>

</template>

<script lang="ts">
    import SidebarCollapse from '@/layout/SidebarCollapse.vue';
    import {Component, Vue} from "vue-property-decorator";
    import axios from "~/http";

    @Component({
        name: 'sidebar',
        components: {
            'sidebar-collapse': SidebarCollapse
        }
    })
    export default class Sidebar extends Vue {
        private menu: object = {
            brands: {
                title: 'Brands',
                items: [
                    {
                        title: 'Create',
                        link: 'brands.create'
                    },
                    {
                        title: 'List',
                        link: 'brands.list'
                    }
                ]
            },
            products: {
                title: 'Products',
                items: [
                    {
                        title: 'Create',
                        link: 'products.create'
                    },
                    {
                        title: 'List',
                        link: 'products.list'
                    }
                ]
            },
            users: {
                title: 'Users',
                items: [
                    {
                        title: 'List',
                        link: 'users.list'
                    }
                ]
            },
            account: {
                title: 'Account',
                items: [
                    {
                        title: 'Logout',
                        link: 'login',
                        listeners: {
                            click: (event: any) => {
                                axios.post('/logout').then(() => {});
                            }
                        }
                    }
                ]
            }
        };
    }
</script>

<style lang="scss" scoped>
    .sidebar {
        position: sticky;
        top: 0;
        left: 0;
        width: 250px;
        min-width: 250px;
        height: 100vh;
        overflow-y: auto;
        border-right: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        z-index: 1;

        &__logo {
            padding: 1rem 2rem;
            width: 100%;
            background-color: #34495e;
            color: #FFFFFF;
            cursor: pointer;

            h5 {
                font-weight: bold;
                text-align: center;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
            }
        }
    }

</style>