import Mock from "mockjs";
import { builder } from "../util";
import topics from './HomePageTopics.json'

Mock.mock(/\/topics\/(\d+)/, "get", (options) => {
  console.debug("接收到topic访问请求，options：", options);
  const id=Number(options.url.match(/\/topics\/(\d+)/)[1])
  console.debug("请求的topic的id：", id);
  if(1126958!==id){
    return builder(null,"fail",404)
  }
  return builder({
    id:id,
    title:'育碧将推出多本《孤岛惊魂》 系列相关出版物',
    abstract:'基本都在《孤岛惊魂6》发售后不久推出。',
    content:'<p>　　育碧旗下主视角射击系列新作《孤岛惊魂6》即将于 2021 年 10 月 7 日登陆 PS5、PS4、Xbox Series X|S、Xbox One、Stadia、亚马逊 Luna、PC 平台，育碧官方现宣布将与多家出版社合作，推出多本《孤岛惊魂》系列相关书籍，为《孤岛惊魂》系列的粉丝们带来全新的故事深入探究系列内核，领略系列大胆的生存幻想世界以及其反派人物们的魅力。&nbsp;&nbsp;<br></p><hr class="vg_hr" contenteditable="false"><h4>《The Art of Far Cry 6》&nbsp;&nbsp;</h4><p>　　本书由黑马图书（Dark Horse Books）和育碧多伦多工作室携手创作，是一本将带领读者们在充满活力的雅拉岛屿上进行一场视觉之旅的精美图书。这本超大开面的全彩图书以概念艺术、人物设计以及令人惊叹的设定及景色为特色，更提供了对于安东·卡斯蒂约政权、“自由武装”游击队及其精巧武器库的广泛见解。《The Art of Far Cry 6》预计将于 2021 年 10 月 12 日发售。</p><p style="text-align: center;"><img style="width:600px;" src="https://img01.vgtime.com/game/cover/2021/06/23/210623144919838_u162815.jpg" class=""><br></p><h4>《Far Cry Rite of Passage》精装版 </h4><p>　　《Far Cry: Rite of Passage》是由黑马推出的官方周边漫画系列，讲述了粉丝们最喜爱的反派，总统安东·卡斯蒂约唯一的儿子迭戈通往权利提升的复杂道路的勇气故事。故事以迭戈刚满 13 岁为开端，但等待他的生日宴不只是一次庆祝活动，更是一次成年礼。安东希望能教授迭戈关于领导力的重要课程，并讲述他所听说的有关三位传奇人物陨落的警示故事，他们分别是范斯·蒙特奈葛、贝根明和约瑟夫·席德。</p><p style="text-align: center;"><img style="width:600px;" src="https://img01.vgtime.com/game/cover/2021/06/23/210623144952235_u162815.jpg" class=""><br></p><p>　　3 期迷你系列中的第一期已经在美国、加拿大和英国地区发行，本次推出的是《Far Cry: Rite of Passage》英文精装本，计划将于 2021 年 11 月 30 日全球发行。该图书由 Bryan Edward Hill 撰写，Geraldo Borges 绘制插图，Michael Atiyeh 上色，Comicraft 印制，封面则由艺术家 Matt Taylor 创作。 <br></p><p style="text-align: center;"><img style="width:600px;" src="https://img01.vgtime.com/game/cover/2021/06/23/210623145013958_u162815.jpg" class=""><br></p><h4>《Far Cry: Esperanza’s Tears》</h4><p>　　胡安·考特兹是《孤岛惊魂6》故事中登场的一名职业游击队员，他的生活并不平静，一直以来他都为世界各地的组织提供专业知识。随着安东·卡斯蒂约的再度掌权，他也再度出山，受雇于由一位“圣科斯塔”（Santa Costa）著名大学教师领导的低调抵抗运动组织，在南美国家“圣科斯塔”，一种名为钽的贵金属吸引了各方势力的兴趣，而胡安最终落入了一个致命陷阱。这部充满肾上腺素的视觉小说将在狂野的火爆动作故事和迷人的人物阵容中重温《孤岛惊魂6》的影视化故事。由 Mathieu Mariolle 撰写，Afif Khaled 以及 Salaheddine Basti 协助完成插图和上色的《Far Cry: Esperanza’s Tears》将于 2021 年 11 月由 Glénat Editions 出版。 &nbsp;&nbsp;<br></p><p style="text-align: center;"><img style="width:600px;" src="https://img01.vgtime.com/game/cover/2021/06/23/210623145023829_u162815.jpg" class=""><br></p><h4>&nbsp; 《The Far Cry Survival Manual》</h4><p>　　《The Far Cry Survival Manual》讲述了记者 Hunter Nash 前往 Rook Islands、蒙大拿镇、雅拉等地的危险旅程，并在一些极端情况和环境下探索和生存的故事。这本书将让读者们沉浸在《孤岛惊魂》的世界中，其中包含了生存、自卫、极限运动、特技驾驶等方面的实用技巧，灵感则来自于读者们所喜爱的游戏。《The Far Cry SurvivalManual》将于 2021 年 9 月 21 日由 Insight Editions 出版。 &nbsp;<br></p><p style="text-align: center;"><img style="width:600px;" src="https://img01.vgtime.com/game/cover/2021/06/23/210623145144423_u162815.jpg" class=""><br></p>',
    editor:{
      name:'Rmisser',
    },
    author:{
      name:'Rmisser',
    },
    time:new Date('2021-06-23 14:43:41').getTime(),
  }, "成功取到topic数据", 200);
});
Mock.mock(/\/topics$/,'get',(options)=>{
  return builder(topics,'给你所有topic',200)
})