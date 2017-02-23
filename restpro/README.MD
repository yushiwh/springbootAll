REST直接访问
localhost:8080  显示的是
{
"_links":{
"persons":{
"href": "http://localhost:8080/persons{?page,size,sort}",
"templated": true
},
"profile":{
"href": "http://localhost:8080/profile"
}
}
}

敲入http://localhost:8080/persons
得到
{
"_embedded":{
"persons":[{"name": "汪云飞", "age": 32, "address": "合肥", "_links":{"self":{"href": "http://localhost:8080/persons/1"…]
},
"_links":{
"first":{
"href": "http://localhost:8080/persons?page=0&size=20"
},
"self":{
"href": "http://localhost:8080/persons"
},
"next":{
"href": "http://localhost:8080/persons?page=1&size=20"
},
"last":{
"href": "http://localhost:8080/persons?page=2&size=20"
},
"profile":{
"href": "http://localhost:8080/profile/persons"
},
"search":{
"href": "http://localhost:8080/persons/search"
}
},
"page":{
"size": 20,
"totalElements": 45,
"totalPages": 3,
"number": 0
}
}

查询为1的数据
敲入http://localhost:8080/persons/1
得到
{
"name": "汪云飞",
"age": 32,
"address": "合肥",
"_links":{
"self":{
"href": "http://localhost:8080/persons/1"
},
"person":{
"href": "http://localhost:8080/persons/1"
}
}
}


将自定义接口暴露成REST后进行查询
输入：localhost:8080/persons/search/nameStartsWith?name=喻适


分页：localhost:8080/persons?page=1&size=2  分页
排序：localhost:8080/persons?sort=age,desc 排序
保存：localhost:8080/persons  POST     参数：{"name":"haha","address":"成都","age":"99"}
修改：localhost:8080/persons/21  PUT   参数：{"name":"haha","address":"成都","age":"99"}
删除：localhost:8080/persons/21  DELETE




