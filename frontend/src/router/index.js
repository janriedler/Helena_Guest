import Vue from "vue";
import VueRouter from "vue-router";
import Table from "../components/Table";
import Add from "../components/Add";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Table",
    component: Table
  },
  {
    path: "/neu",
    name: "Add",
    component: Add
  }
];

const router = new VueRouter({
  routes
});

export default router;
