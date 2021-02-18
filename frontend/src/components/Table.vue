<template>
  <div style="display: flex; flex-direction: column;">
    <div
      v-if="!profil"
      style="display: flex; flex-direction: row; justify-content: center; align-items: center; margin: 40px"
    >
      <div>
        <el-input
          v-model="search"
          @keyup.enter.native="doSearch()"
          placeholder="Suche Name/Email/Sonstiges"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="doSearch()"
          ></el-button>
        </el-input>
      </div>
    </div>
    <div v-if="!profil">
      <el-select v-model="sort" placeholder="Sortiere nach" @change="sortiere">
        <el-option label="Namen" value="namen"></el-option>
        <el-option label="Check-In" value="date"></el-option>
        <el-option label="zuletzt hinzugefügt" value="id"></el-option>
      </el-select>
    </div>
    <div v-if="!profil" class="table">
      <el-table :data="tableData" onclick="console.log()" style="width: 100%">
        <el-table-column
          v-for="column in columns"
          :key="column.label"
          :prop="column.prop"
          :label="column.label"
        >
          <template slot-scope="scope">
            <el-button
              v-if="column.label === 'Name'"
              @click.native="callId(scope.row.id)"
              >{{ scope.row.name }}</el-button
            >
            <span v-else>{{ scope.row[column.prop] }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div
      v-if="profil"
      style="display: flex; flex-direction: row; justify-content: center; align-items: center;"
    >
      <el-button @click="getData()" style="margin-top: 5%">Zurück</el-button>
    </div>
    <div v-if="profil" class="table">
      <el-form ref="form" label-width="120px" style="margin-top: 5%">
        <el-form-item label="Name:" style="border:1px solid #000066;">
          <el-input v-if="edit" v-model="name"></el-input>
          <span v-else style="float: left">{{ name }}</span>
        </el-form-item>
        <el-form-item label="Email:" style="border:1px solid #000066">
          <el-input v-if="edit" v-model="email"></el-input>
          <span v-else style="float: left">{{ email }}</span>
        </el-form-item>
        <el-form-item label="Preis:" style="border:1px solid #000066">
          <el-input v-if="edit" v-model="preis"></el-input>
          <span v-else style="float: left">{{ preis }}</span>
        </el-form-item>
        <el-form-item label="Gezahlt:" style="border:1px solid #000066">
          <el-input v-if="edit" v-model="gezahlt"></el-input>
          <span v-else style="float: left">{{ gezahlt }}</span>
        </el-form-item>
        <el-form-item label="Raum:" style="border:1px solid #000066">
          <el-input v-if="edit" v-model="raum"></el-input>
          <span v-else style="float: left">{{ raum }}</span>
        </el-form-item>
        <el-form-item label="Check-In:" style="border:1px solid #000066">
          <el-date-picker
            v-if="edit"
            v-model="checkIn"
            type="date"
            placeholder="Check-In"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            style="float: left"
          >
          </el-date-picker>
          <span v-else style="float: left">{{ checkIn }}</span>
        </el-form-item>
        <el-form-item label="Check-Out:" style="border:1px solid #000066">
          <el-date-picker
            v-if="edit"
            v-model="checkOut"
            type="date"
            placeholder="Check-In"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            style="float: left"
          >
          </el-date-picker>
          <span v-else style="float: left">{{ checkOut }}</span>
        </el-form-item>
        <el-form-item label="Sonstiges:" style="border:1px solid #000066">
          <el-input v-if="edit" v-model="sonstiges"></el-input>
          <span v-else style="float: left">{{ sonstiges }}</span>
        </el-form-item>
        <el-form-item>
          <el-button v-if="edit" @click="this.submit">Speichern</el-button>
          <el-button v-else @click="this.change">Bearbeiten</el-button>
          <el-button @click="this.delID">Löschen</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      sort: "",
      edit: false,
      id: "",
      email: "",
      preis: "",
      raum: "",
      checkIn: "",
      checkOut: "",
      gezahlt: "",
      name: "",
      sonstiges: "",
      search: "",
      profil: false,
      columns: [
        {
          prop: "name",
          label: "Name"
        },
        {
          prop: "email",
          label: "Email"
        },
        {
          prop: "gezahlt/preis",
          label: "Gezahlt / Preis"
        }
      ],
      tableData: [],
      tableDataOrg: []
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      this.profil = false;
      this.edit = false;
      let sortAfter = "";
      if (this.sort === "") {
        sortAfter = "id";
      } else {
        sortAfter = this.sort;
      }

      axios.get("/all", { params: { sort: sortAfter } }).then(response => {
        const unprocessed = JSON.parse(JSON.stringify(response.data));
        let rows = [];
        for (let j = 0; j < unprocessed.length; j++) {
          const obj = {
            name: unprocessed[j]["name"],
            email: unprocessed[j]["email"],
            id: unprocessed[j]["id"],
            sonstiges: unprocessed[j]["sonstiges"],
            "gezahlt/preis":
              unprocessed[j]["gezahlt"] + "/" + unprocessed[j]["preis"]
          };
          rows.push(obj);
        }
        this.tableDataOrg = rows;
        this.tableData = rows;
      });
    },
    sortiere() {
      this.getData();
    },
    doSearch() {
      this.tableData = this.tableDataOrg;
      let filterRows = [];
      for (let i = 0; i < this.tableData.length; i++) {
        let cellName = JSON.parse(JSON.stringify(this.tableData[i]["name"]));
        let cellEmail = JSON.parse(JSON.stringify(this.tableData[i]["email"]));
        let cellSonst = JSON.parse(
          JSON.stringify(this.tableData[i]["sonstiges"])
        );
        if (cellName.toLowerCase().includes(this.search.toLowerCase())) {
          filterRows.push(this.tableData[i]);
        } else if (
          cellEmail.toLowerCase().includes(this.search.toLowerCase())
        ) {
          filterRows.push(this.tableData[i]);
        } else if (
          cellSonst.toLowerCase().includes(this.search.toLowerCase())
        ) {
          filterRows.push(this.tableData[i]);
        }
      }
      this.tableData = filterRows;
      this.search = "";
    },
    callId(id) {
      this.profil = true;
      axios.get("/id", { params: { id: id } }).then(response => {
        const unprocessed = JSON.parse(JSON.stringify(response.data));
        console.log(unprocessed);
        this.id = unprocessed["id"];
        this.name = unprocessed["name"];
        this.email = unprocessed["email"];
        this.checkIn = unprocessed["checkIn"];
        this.checkOut = unprocessed["checkOut"];
        this.raum = unprocessed["raum"];
        this.preis = unprocessed["preis"];
        this.gezahlt = unprocessed["gezahlt"];
        this.sonstiges = unprocessed["sonstiges"];
      });
    },
    submit() {
      let all = [];
      all.push(
        this.id,
        this.name,
        this.email,
        this.checkIn,
        this.checkOut,
        this.sonstiges,
        this.raum,
        this.preis,
        this.gezahlt
      );
      axios
        .get("/edit", {
          params: {
            id: this.id,
            name: this.name,
            email: this.email,
            checkIn: this.checkIn,
            checkOut: this.checkOut,
            sonstiges: this.sonstiges,
            raum: this.raum,
            preis: this.preis,
            gezahlt: this.gezahlt
          }
        })
        .then(response => {
          console.log(response);
        });

      this.profil = false;
      this.edit = false;
      this.getData();
    },
    change() {
      this.edit = true;
    },
    delID() {
      console.log("id:" + this.id)
      axios
        .get("/delID", {
          params: {
            id: this.id
          }
        })
        .then(response => {
          console.log(response);
        });
      this.profil = false;
      this.edit = false;
      this.getData();
    }
  }
};
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.el-tooltip__popper {
  max-width: 500px;
}
</style>
