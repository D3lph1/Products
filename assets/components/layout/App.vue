<template>
    <div id="app-content">
        <notifications></notifications>
        <router-view></router-view>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import Notifications from '@/common/Notifications.vue';

    @Component({
        name: 'App',
        components: {
            'notifications': Notifications
        }
    })
    export default class App extends Vue {
    }
</script>
