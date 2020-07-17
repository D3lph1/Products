<template>
    <div class="content">
        <div class="container py-4 h-100 d-flex flex-column">
            <h1 v-if="$route.meta.title_primary">{{ $route.meta.title_primary }}</h1>

            <h5
                    v-if="$route.meta.title_secondary"
                    class="mb-4"
            >{{ $route.meta.title_secondary }}</h5>

            <router-view></router-view>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component({name: 'a-content'})
    export default class AContent extends Vue {
    }
</script>

<style scoped>
    .content {
        flex-grow: 1;
    }
</style>
