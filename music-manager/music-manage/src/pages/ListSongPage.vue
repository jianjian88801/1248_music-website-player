<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets"></i>歌单歌曲信息
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入歌曲名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="680px" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="name" label="歌手-歌名" align="center"></el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
<!--            <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf">-->
<!--                <el-form-item prop="singerName" label="歌手名字" size="mini">-->
<!--                    <el-input v-model="registerForm.singerName" placeholder="歌手名字"></el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item prop="songName" label="歌曲名字" size="mini">-->
<!--                    <el-input v-model="registerForm.songName" placeholder="歌曲名字"></el-input>-->
<!--                </el-form-item>-->
<!--            </el-form>-->
          歌手：<el-select v-model="value" filterable placeholder="请选择" @focus="showSingerName">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>
          歌曲：<el-select v-model="song" filterable placeholder="请选择" @focus="showSongNameBySingerName">
          <el-option
            v-for="item in songOptions"
            :key="item.songValue"
            :label="item.songLabel"
            :value="item.songValue">
          </el-option>
          </el-select>

            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addSong">确定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="删除歌曲" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复，是否确定删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="delVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from '../mixins/index';
import {
  listSongDetail,
  songOfSongId,
  songOfSongName,
  listSongAdd,
  delListSong,
  getAllSinger,
  songOfSingerId
} from '../api/index';

export default {
    mixins: [mixin],
    data(){
        return{
            centerDialogVisible: false, //添加弹窗是否显示
            delVisible: false,          //删除弹窗是否显示
            // registerForm:{      //添加框
            //     singerName: '',     //歌手名字
            //     songName: ''        //歌曲名字
            // },
            options: [

            ],
            value:'',
            singerName:'',
            songOptions: [

            ],
            song:'',
            songName:'',
            tableData: [],
            tempData: [],
            select_word: '',
            idx: -1,          //当前选择项
            multipleSelection: [],   //哪些项已经打勾
            songListId: ''          //歌单id
        }
    },
    watch:{
        //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
        select_word: function(){
            if(this.select_word == ''){
                this.tableData = this.tempData;
            }else{
                this.tableData = [];
                for(let item of this.tempData){
                    if(item.name.includes(this.select_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.songListId = this.$route.query.id;
        this.getData();
    },
    methods:{
      //根据歌手id查询歌曲名称列表
      showSongNameBySingerName(){
        songOfSingerId(this.value).then(res=>{
          this.songOptions=res.filter(function (s){
            return s.songValue=s.id,s.songLabel=s.name;
          });
        })
      },
      //查询所有歌手列表
        showSingerName(){
          getAllSinger().then(res => {
            this.options=res.filter(function (s){
              return s.value=s.id,s.label=s.name;
            })
          })
        },
        //根据歌单id查询歌曲列表
        getData(){
            this.tempData = [];
            this.tableData = [];
            listSongDetail(this.songListId).then(res => {
                for(let item of res){
                    this.getSong(item.songId);
                }
            })
        },
        //根据歌曲id查询歌曲对象，放到tempData和tableData里面
        getSong(id){
            songOfSongId(id)
            .then(res => {
                this.tempData.push(res);
                this.tableData.push(res);
            })
            .catch(err => {
                console.log(err);
            });
        },
        //添加歌曲前的准备，获取到歌曲id
        // getSongId(){
        //     let _this = this;
        //     var songOfName = _this.registerForm.singerName+"-"+_this.registerForm.songName;
        //     songOfSongName(songOfName).then(
        //         res => {
        //             _this.addSong(res[0].id)
        //         }
        //     )
        // },
        //添加歌曲
        addSong(){
            let _this = this;
            let params = new URLSearchParams();
            params.append('songId',this.song);
            params.append('songListId',this.songListId);

            listSongAdd(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("添加成功","success");
                }else{
                    this.notify("添加失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            _this.centerDialogVisible = false;
        },
        //删除一条歌曲
        deleteRow(){
            delListSong(this.idx,this.songListId)
            .then(res => {
                if(res){
                    this.getData();
                    this.notify("删除成功","success");
                }else{
                    this.notify("删除失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.delVisible = false;
        }
    }
}
</script>

<style scoped>
    .handle-box{
        margin-bottom: 0px;
    }
    .song-img{
        width: 100%;
        height: 80px;
        border-radius: 5px;
        margin-bottom: 5px;
        overflow: hidden;
    }
    .handle-input{
        width: 300px;
        display: inline-block;
    }
    .pagination{
        display: flex;
        justify-content: center;
    }
    .play {
        position: absolute;
        z-index: 100;
        width: 80px;
        height: 80px;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        top: 18px;
        left: 15px;
    }

    .icon {
        width: 2em;
        height: 2em;
        color: white;
        fill: currentColor;
        overflow: hidden;
    }
    .crumbs{
        margin: 20px;
    }
</style>
