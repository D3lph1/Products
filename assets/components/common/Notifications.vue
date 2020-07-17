<template>
    <div class="notifications">
        <b-alert
                v-for="(item, index) in notifications"
                class="notification"
                :variant="item.type"
                :key="'a' + index"
                :show="dismissCountDown"
                @dismiss-count-down="countDownChanged"
        >
            <p v-html="item.message"></p>
            <b-progress
                    v-if="index === notifications.length - 1"
                    :variant="item.type"
                    :max="dismissSecs"
                    :value="dismissCountDown"
                    height="4px"
            ></b-progress>
        </b-alert>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import Notificator, {Notification} from "~/notificator";

    @Component({
        name: 'notifications'
    })
    export default class Notifications extends Vue {
        private dismissSecs: number = 4;
        private dismissCountDown: number = 0;

        private get notifications(): Array<Notification> {
            this.dismissCountDown = this.dismissSecs;
            return this.$store.state.notifications;
        }

        private countDownChanged(dismissCountDown: number) {
            this.dismissCountDown = dismissCountDown;
            if (dismissCountDown === 0) {
                Notificator.clear();
            }
        }
    }
</script>

<style lang="scss" scoped>
    .notifications {
        position: fixed;
        top: 1rem;
        right: 1rem;
        z-index: 10000;
    }

    .notifications .notification {
        position: relative;
        padding: 0.5rem 1.5rem 0.5rem 1rem;
        min-width: 240px;
        max-width: 320px;
        margin-bottom: 0.5rem;
    }
</style>
