<template>
    <div class="default">
        <sidebar></sidebar>
        <a-content></a-content>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Sidebar from '@/layout/Sidebar.vue'
    import AContent from '@/layout/Content.vue'

    @Component({
        name: 'main-layout',
        components: {
            'sidebar': Sidebar,
            'a-content': AContent
        }
    })
    export default class MainLayout extends Vue {

    }
</script>

<style scoped>
    .default {
        min-height: 100%;
        display: flex;
        flex-wrap: nowrap;
    }
</style>
