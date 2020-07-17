<template>
    <b-button
            class="button-loading"
            :disabled="loading || disabled"
            v-bind="$attrs"
            v-on="$listeners"
    >
        <span :style="{ opacity: Number(!loading) }">
          <slot/>
        </span>

        <span v-show="loading" class="button-loading__spinner">
          <b-spinner small/>
        </span>
    </b-button>
</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator'

    @Component({
        name: 'ButtonLoading'
    })
    export default class ButtonLoading extends Vue {
        @Prop(Boolean)
        private loading!: boolean;

        @Prop(Boolean)
        private disabled!: boolean;
    }
</script>

<style lang="scss" scoped>
    .button-loading {
        position: relative;

        &__spinner {
            position: absolute;
            display: flex;
            justify-content: center;
            align-items: center;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }
    }
</style>
