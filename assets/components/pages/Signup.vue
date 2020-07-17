<template>
    <div style="height: 100%">
        <div class="login">
            <b-card class="login__card" title="Sign-up">
                <b-form @submit.prevent="submit">
                    <b-form-group>
                        <b-form-input
                                v-model="email"
                                type="text"
                                placeholder="Email"
                                :state="validEmail"
                        />
                        <b-form-text>Your email address</b-form-text>
                    </b-form-group>

                    <b-form-group>
                        <b-form-input
                                v-model="password"
                                type="password"
                                placeholder="Password"
                                :state="validPassword"
                        />
                        <b-form-text id="input-live-help"><b-form-text>Strong password at least 6 symbols length</b-form-text></b-form-text>
                    </b-form-group>

                    <b-form-group>
                        <b-form-input
                                v-model="passwordConfirmation"
                                type="password"
                                placeholder="Password again"
                                :state="validPasswordConfirmation"
                        />
                        <b-form-text id="input-live-help"><b-form-text>Password again not to forget</b-form-text></b-form-text>
                    </b-form-group>

                    <button-loading
                            class="mb-3"
                            variant="primary"
                            block
                            type="submit"
                            :disabled="disabled"
                            :loading="loading"
                    >
                        <b-icon-person></b-icon-person>
                        Create
                    </button-loading>

                    <div class="text-right">
                        <b-link :to="{name: 'login'}">
                            Already with us?
                        </b-link>
                    </div>
                </b-form>
            </b-card>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Watch} from 'vue-property-decorator'
    import ButtonLoading from '@/common/ButtonLoading.vue';
    import axios, {ApiResponse, hasFormErrorWithField} from "~/http";
    import {AxiosError, AxiosResponse} from "axios";
    import Notificator from "~/notificator";

    @Component({
        components: {
            'button-loading': ButtonLoading
        }
    })
    export default class Login extends Vue {
        private readonly EMAIL_REGEX = /^.+@.+\..+$/;

        private email: string = '';
        private password: string = '';
        private passwordConfirmation: string = '';
        private loading: boolean = false;
        private emailTouched: boolean = false;
        private emailConflict: boolean|null = null;
        private passwordTouched: boolean = false;
        private passwordConfirmationTouched: boolean = false;

        @Watch('email')
        private onEmailChanged(): void {
            this.emailTouched = true;
            this.emailConflict = null;
        }

        @Watch('password')
        private onPasswordChanged(): void {
            this.passwordTouched = true;
        }

        @Watch('passwordConfirmation')
        private onPasswordConfirmationChanged(): void {
            this.passwordConfirmationTouched = true;
        }

        private get disabled(): boolean {
            return !this.validEmail || this.password === '' || this.passwordConfirmation === ''
                || this.password !== this.passwordConfirmation;
        }

        private get validEmail(): boolean | null {
            if (!this.emailTouched) {
                return null;
            }

            if (this.emailConflict === true) {
                return false;
            }

            return this.EMAIL_REGEX.test(this.email);
        }

        private get validPassword(): boolean | null {
            if (!this.passwordTouched) {
                return null;
            }

            return this.password.length >= 6;
        }

        private get validPasswordConfirmation(): boolean | null {
            if (this.validPassword === null || !this.passwordConfirmationTouched) {
                return null;
            }

            return this.validPassword && this.password === this.passwordConfirmation
        }

        private submit(): void {
            this.loading = true;
            this.emailConflict = true;

            axios.post('/signup', {
                email: this.email,
                password: this.password
            })
                .then(
                    (response: AxiosResponse<ApiResponse>) => {
                        if (response.data.ok) {
                            this.$router.push({name: 'login'})
                        }

                        this.loading = false;
                    }
                )
                .catch((err: any) => {
                    this.loading = false;
                    if (hasFormErrorWithField(err.response.data.errors, 'email')) {
                        this.emailConflict = true;
                    }
                })
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
