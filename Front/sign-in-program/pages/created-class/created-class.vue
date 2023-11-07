<template>
	<view>
		<view class="top-view">
			<text>班级列表</text>
			<van-button size="small" type="info" @click="gotoCreateClass">创建班级</van-button>
		</view>
		<view class="body">
			<my-class @click.native="gotoMyCreatedClass"></my-class>
			<my-class @click.native="gotoMyCreatedClass"></my-class>
			<my-class @click.native="gotoMyCreatedClass"></my-class>
			<my-class @click.native="gotoMyCreatedClass"></my-class>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			gotoCreateClass(){
				uni.navigateTo({
					url: '/subpkg/toCreateClass/toCreateClass'
				});
			},
			gotoMyCreatedClass(){
				uni.navigateTo({
					url:'/subpkg/teacher_in_class/teacher_in_class'
				})
			}
		}
	}
</script>

<style lang="scss">
.top-view{
	height: 60px;
	display: flex;
	justify-content: space-between;
	border-bottom:1px solid  rgba(229, 231, 235, 1);
	text{
		padding-left: 10px;
		padding-top: 15px;
		font-size: 20px;
		font-weight: bolder;
	}
	van-button{
		padding-right: 10px;
		padding-top: 15px;
	}
}
.body{
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
}
</style>
