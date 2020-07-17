<template>
    <div class="index">
        <div class="mr-4">
            <b-icon-arrow-right class="index__icon mr-2" :variant="hover ? 'dark' : 'primary'" animation="cylon"></b-icon-arrow-right>
            <router-link to="/products">
                <b-icon-box class="index__icon" @mouseenter="hover=true" @mouseleave="hover=false"></b-icon-box>
            </router-link>
        </div>
        <h1 class="text-left">You're welcome, let's start</h1>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'

    @Component
    export default class Index extends Vue {
        private hover: boolean = false;
    }
</script>

<style lang="scss" scoped>
    @import "assets/scss/imports";

    .index {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;

        &__icon {
            font-size: 80px;
        }
    }

    a {
        text-decoration: none;
        color: $gray-900;

        :hover {
            color: $primary;
        }
    }
</style>
