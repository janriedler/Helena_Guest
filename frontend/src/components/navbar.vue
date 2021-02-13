<template>
  <el-row>
    <el-col :span="4" class="navbar__logo-box">
      <img :src="image" alt="" class="navbar__logo">
    </el-col>
    <el-col :span="20" class="navbar__menu-box">
      <ul class="el-menu el-menu--horizontal navbar__menu">
        <li class="el-menu-item"><router-link to="neu"> Neuer Eintrag</router-link></li>
      </ul>
      <p class="navbar__title" style="margin-right: 22% ">
        <router-link to="/">Isolabella</router-link>
      </p>
    </el-col>
  </el-row>
</template>
<script>

export default {
  data () {
    return {
      image: require('../assets/logo.jpg')
    }
  },
  mounted () {

  },
  methods: {
  }
}
</script>
<style>
.navbar__logo {
  display: block;
  height: 60px;
  margin-left: auto;
  margin-right: auto;
}

.navbar__menu-box {
  background-color: #fff;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}

.navbar__menu {
  float: right;
  background-color: #fff;
}

.navbar__dropdown-menu a {
  color: inherit;
  text-decoration: none;
}

.navbar__title {
  margin-top: 0;
  margin-bottom: 0;
  margin-left: 12px;
  height: 60px;
  line-height: 60px;
  font-size: 1.5em;
  font-weight: bold;
}

.navbar__title a {
  color: #48576a;
  text-decoration: none;
}
</style>
