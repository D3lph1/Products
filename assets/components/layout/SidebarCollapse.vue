<template>
    <div class="sidebar-collapse">
        <div
                class="sidebar-collapse__head"
                :class="{ 'sidebar-collapse__head_active': isCollapseOpened }"
                @click="toggleCollapse"
        >{{ title }}
        </div>

        <BListGroup
                v-if="items.length"
                v-show="isCollapseOpened"
                flush
        >
            <BListGroupItem
                    v-for="{ title, link, listeners = {} } in items"
                    :key="title"
                    :to="{name: link}"
                    v-on="listeners"
            >{{ title }}
            </BListGroupItem>
        </BListGroup>
    </div>

</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator'

    @Component({
        name: 'collapse'
    })
    export default class Collapse extends Vue {
        @Prop(String)
        private title!: string;

        @Prop(Array)
        private items!: Array<object>;

        private isCollapseOpened: boolean = false;

        private toggleCollapse(): void {
            this.isCollapseOpened = !this.isCollapseOpened;
        }
    }
</script>

<style lang="scss" scoped>
    .sidebar-collapse {
        border-bottom: 1px solid #bdc3c7;
        user-select: none;

        &__head {
            padding: 10px;
            font-weight: 500;
            cursor: pointer;
            transition: background-color .1s;

            &:hover {
                background-color: #ecf0f1;
            }

            &_active {
                background-color: #ecf0f1;
            }
        }
    }
</style>