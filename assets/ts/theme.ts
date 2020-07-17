import Vue from "vue";

import '../scss/imports.scss'
import './../scss/app.scss'

import {
    BootstrapVue,
    LayoutPlugin,
    ModalPlugin,
    FormPlugin,
    FormInputPlugin,
    FormSelectPlugin,
    FormDatepickerPlugin,
    FormTimepickerPlugin,
    FormGroupPlugin,
    ButtonPlugin,
    InputGroupPlugin,
    CardPlugin,
    TablePlugin,
    PaginationPlugin,
    LinkPlugin,
    ListGroupPlugin,
    AlertPlugin,
    ProgressPlugin,
    TimePlugin,
    SpinnerPlugin,

    BIconArrowRight,
    BIconBox,
    BIconTrash,
    BIconPencil,
    BIconSearch,
    BIconUnlock,
    BIconPerson,
    BIconPlus
} from 'bootstrap-vue'

Vue.use(LayoutPlugin);
Vue.use(ModalPlugin);
Vue.use(FormPlugin);
Vue.use(FormInputPlugin);
Vue.use(FormSelectPlugin);
Vue.use(FormDatepickerPlugin);
Vue.use(FormTimepickerPlugin);
Vue.use(TimePlugin);
Vue.use(FormGroupPlugin);
Vue.use(ButtonPlugin);
Vue.use(InputGroupPlugin);
Vue.use(CardPlugin);
Vue.use(TablePlugin);
Vue.use(PaginationPlugin);
Vue.use(LinkPlugin);
Vue.use(ListGroupPlugin);
Vue.use(AlertPlugin);
Vue.use(ProgressPlugin);
Vue.use(SpinnerPlugin);

Vue.component('b-icon-arrow-right', BIconArrowRight);
Vue.component('b-icon-box', BIconBox);
Vue.component('b-icon-trash', BIconTrash);
Vue.component('b-icon-pencil', BIconPencil);
Vue.component('b-icon-search', BIconSearch);
Vue.component('b-icon-unlock', BIconUnlock);
Vue.component('b-icon-person', BIconPerson);
Vue.component('b-icon-plus', BIconPlus);
