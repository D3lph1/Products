<template>
    <div class="index">
        <div class="mr-4">
            <b-icon class="index__icon mr-2" icon="arrow-right" :variant="hover ? 'dark' : 'primary'" animation="cylon"></b-icon>
            <router-link to="/products">
                <b-icon class="index__icon" icon="box" @mouseenter="hover=true" @mouseleave="hover=false"></b-icon>
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
