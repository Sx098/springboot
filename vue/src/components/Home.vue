<template>
  <div style="padding: 10px; width: 90%">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div style="margin: 10px 0; display: flex; width: 30%">
      <el-input v-model="search" placeholder="请输入内容"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadTable(2)">搜索</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable> </el-table-column>
      <el-table-column prop="name" label="姓名"> </el-table-column>
      <el-table-column prop="age" label="年龄"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="danger" size="small"
            >编辑</el-button
          >
          <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
            <el-button
              slot="reference"
              type="text"
              size="small"
              style="margin-left: 5px"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @prev-click="loadTable"
        @current-change="loadTable"
        @next-click="loadTable"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
      <el-dialog title="用户信息" :visible.sync="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px" ref="form">
          <el-form-item label="用户名">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
const axios = require("axios");
export default {
  name: "Home",
  methods: {
    loadTable(num) {
      this.currentPage = num;
      axios
        .get(`api/user/page?pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
        .then((res) => {
          this.tableData = res.data.data.records;
        })
        .catch((err) => {
          console.error(err);
        });
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
    },
    handleClick(row) {
      this.form = row;
      this.dialogVisible = true;
    },
    del(id) {
      console.log(id);
      axios
        .delete(`api/user/${id}`)
        .then((res) => {
          console.log(res);
          this.loadTable(1);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    save() {
      let data = JSON.stringify(this.form);
      if (this.form.id) {
        //编辑
        axios({
          method: "put",
          url: "api/user",
          data: data,
          headers: {
            "Content-Type": "application/json",
          },
        })
          .then((res) => {
            console.log(res);
            this.dialogVisible = false;
            this.loadTable(1);
          })
          .catch((err) => {
            console.log(err);
          });
      }
      else{
        axios({
          method: "post",
          url: "api/user",
          data: data,
          headers: {
            "Content-Type": "application/json",
          },
        })
          .then((res) => {
            console.log(res);
            this.dialogVisible = false;
            this.loadTable(1);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    handleSizeChange(num) {
      this.pageSize=num
      this.loadTable(this.currentPage)
    }
  },
  created() {
    this.loadTable(this.currentPage);
  },
  data() {
    return {
      form: {},
      tableData: [],
      dialogVisible: false,
      search: "",
      currentPage: 1,
      pageSize:8,
      total: 100
    };
  },
};
</script>
<style>
</style>