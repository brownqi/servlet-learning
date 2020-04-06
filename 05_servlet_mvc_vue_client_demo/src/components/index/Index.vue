<template>
  <div class="index">
    <h1>主页</h1>
    <div class="search">
      <input class="search-text" type="text" v-model="word">
      <button class="search-btn" @click="_search">搜索</button>
    </div>
    <div class="goods">
      <table cellpadding="0" cellspacing="0">
        <thead>
        <tr>
          <th>GOOD-ID</th>
          <th>GOOD-NAME</th>
          <th>GOOD-PRICE</th>
          <th>GOOD-COUNT</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(good,index) in goodList" :key="index">
          <td>{{good.goodid}}</td>
          <td>{{good.goodname}}</td>
          <td>{{good.goodprice}}</td>
          <td>{{good.goodcount}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        goods: [],
        goodList:[],
        word:""
      }
    },
    created() {
      this.axios.get("http://localhost:8080/goodServlet/index").then(resp => {
        this.goods = resp.data;
        this.goodList = this.goods.data;
      })
    },
    methods:{
      _search(){
        this.goodList = this.goods.data.filter(e=>{
          return  e.goodname.indexOf(this.word) !== -1
        })
      }
    },
    name: "index"
  }
</script>
<style lang="scss" scoped>
  .index {
    .search {
      width: 1000px;
      margin: 0 auto;
      text-align: center;
      .search-text {
        width: 300px;
        height: 40px;
        line-height: 40px;
        outline: none;
        border: 1px solid #666666;
        border-radius: 5px 0 0 5px;
        font-size: 18px;
        text-indent: 10px;
      }
      .search-btn {
        width: 50px;
        height: 44px;
        line-height: 40px;
        outline: none;
      }
    }
    .goods {
      width: 1000px;
      margin: 0 auto;
      table {
        width: 100%;
        text-align: center;
        tr {
          height: 40px;
          line-height: 40px;
          th {
            border-bottom: 1px double #333333;
          }
          td {
            border-bottom: 1px solid #333333
          }
        }
      }
    }
  }
</style>
