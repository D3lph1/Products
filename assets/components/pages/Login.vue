<template>
    <div style="height: 100%">
        <div class="login">
            <b-card class="login__card" title="Login">
                <b-form @submit.prevent="submit">
                    <b-form-group>
                        <b-form-input
                                v-model="email"
                                type="text"
                                placeholder="Email"
                        />
                    </b-form-group>

                    <b-form-group>
                        <b-form-input
                                v-model="password"
                                type="password"
                                placeholder="Password"
                        />
                    </b-form-group>

                    <button-loading
                            class="mb-3"
                            variant="primary"
                            block
                            type="submit"
                            :disabled="disabled"
                            :loading="loading"
                    >
                        <b-icon-unlock></b-icon-unlock>
                        Login
                    </button-loading>

                    <div class="text-right">
                        <b-link :to="{name: 'signup'}">
                            First time?
                        </b-link>
                    </div>
                </b-form>
            </b-card>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator'
    import ButtonLoading from '@/common/ButtonLoading.vue';
    import axios, {ApiResponse, UserApiResponse} from '~/http'
    import {AxiosResponse} from "axios";
    import Notificator from "~/notificator";

    @Component({
        components: {
            'button-loading': ButtonLoading
        }
    })
    export default class Login extends Vue {
        private email: string = '';
        private password: string = '';
        private loading: boolean = false;

        get disabled(): boolean {
            return this.email === '' || this.password === '';
        }

        private submit(): void {
            this.loading = true;
            axios.post('login', null, {
                params: {
                    email: this.email,
                    password: this.password
                }
            })
                .then(
                    (response: AxiosResponse<UserApiResponse>) => {
                        if (response.data.ok) {
                            this.$store.commit('authenticate', {user: response.data.user});
                            this.$router.push({name: 'index'});
                        } else {
                            this.loading = false
                        }
                    }
                )
                .catch(() => this.loading = false)
        }
    }
</script>

<style lang="scss">
    .login {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;

        &__card {
            width: 100%;
            max-width: 300px;
        }
    }
</style>

