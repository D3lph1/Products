<template>
    <b-form>
        <b-form-group>
            <b-form-input v-model="name" placeholder="Name"></b-form-input>
        </b-form-group>
        <b-form-group>
            <button-loading class="mt-3" variant="primary" :loading="loading" @click="submit">Create brand</button-loading>
        </b-form-group>
    </b-form>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import ButtonLoading from "../common/ButtonLoading.vue";
    import axios, {ApiResponse} from "~/http";
    import {AxiosResponse} from "axios";

    @Component({
        components: {
            'button-loading': ButtonLoading
        }
    })
    export default class CreateBrand extends Vue {
        private name: string = '';

        private loading: boolean = false;

        private submit(): void {
            this.loading = true;

            axios.post('/api/brands', {name: this.name})
                .then((response: AxiosResponse<ApiResponse>) => {
                    if (response.data.ok) {
                        this.$router.push({name: 'brands.list'})
                    } else {
                        this.loading = false;
                    }
                })
                .catch(() => this.loading = false)
        }
    }
</script>
