<template>
    <el-form ref="form"  label-width="120px" style="margin-top: 5%">
        <el-form-item label="Name">
          <el-input v-model="name"></el-input>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="email"></el-input>
        </el-form-item>
        <el-form-item label="Preis">
          <el-input v-model="preis"></el-input>
        </el-form-item>
        <el-form-item label="Gezahlt">
          <el-input v-model="gezahlt"></el-input>
        </el-form-item>
        <el-form-item label="Raum">
          <el-input v-model="raum"></el-input>
        </el-form-item>
        <el-form-item label="Check-In">
          <el-date-picker v-model="checkIn" type="date" placeholder="Check-In" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="float: left">
          </el-date-picker>
          <!--<el-input v-model="checkIn"></el-input>-->
        </el-form-item>
        <el-form-item label="Check-Out">
          <el-date-picker v-model="checkOut" type="date" placeholder="Check-Out" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="float: left">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="Sonstiges">
          <el-input v-model="sonstiges"></el-input>
        </el-form-item>
      <el-form-item>
        <el-button @click="this.onSubmit">Create</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
import axios from "axios";

export default {
  name: "Add",
  data: () => ({
    email: '',
    preis: '',
    raum: '',
    checkIn: '',
    checkOut: '',
    gezahlt: '',
    name: '',
    sonstiges: '',
  }),
  methods: {
    onSubmit () {
      if (this.name !== "") {
        let all = []
        all.push(this.name, this.email, this.checkIn, this.checkOut, this.sonstiges, this.raum, this.preis, this.gezahlt)
        console.log(all);
        axios
            .get('/add', { params: {name: this.name, email:this.email, checkIn: this.checkIn,
                checkOut: this.checkOut, sonstiges: this.sonstiges, raum: this.raum, preis: this.preis, gezahlt: this.gezahlt} })
            .then((response) => {
              console.log(response)
            })
        this.$router.push('/')
      } else {
        alert("Name darf nicht leer sein")
      }
    },
    resetForm () {
      this.email = '';
      this.raum = '';
      this.checkIn = '';
      this.checkOut = '';
      this.name = '';
      this.preis = '';
      this.gezahlt = '';
      this.sonstiges = '';
    }
  }
};
</script>
