const scriptName = "기웅잇";

const a = '이상민';
const b = "김현우";
const c = "조승묵";

const days = ['일','월','화','수','목','금','토']

const criteria = new Date(2023, 0, 30);
 
const regex = /\d{8} 근무/;


function response(room, msg, sender, isGroupChat, replier, imageDB, packageName) {
  if(room=="전기 카톡방"){
    return;
  }

  var targetDay;
  
  
  if(msg == '금일근무'){
    targetDay = new Date();
    replier.reply(room, getTargetDayWorkers(targetDay));
  }
  
  else if(regex.test(msg)){
    const yyyy = msg.substr(0, 4);
    const mm = parseInt(msg.substr(4, 2));
    const dd = msg.substr(6, 2);
    targetDay = new Date(yyyy,mm-1,dd);
    if(mm-1 != targetDay.getMonth()){
      replier.reply(room, '날짜를 잘못 입력했습니다. 다시 확인해주세요.');
    }
    replier.reply(room, getTargetDayWorkers(targetDay));
  }
}

function getTargetDayWorkers(td){

  const diffTime = td.getTime() - criteria.getTime();
  const diffDate = Math.floor(diffTime / (1000*60*60*24));
  
  if(diffTime < 0) return "23년 1월 30일 이전은 안됨";
  if(td.getDay() == 0) return enterWorker(td, '휴무', '휴무', '휴무');
  if(diffTime == 0) return enterWorker(td, a, b, c);
  
  const diffWeek = Math.floor(diffDate/7);
  const routineType = diffWeek % 3;
  
  switch(routineType){
    case(0):
    return enterWorker(td, a, b, c);
    case(1):
    return enterWorker(td, c, a, b);
    case(2):
    return enterWorker(td, b, c, a);
  }
}

function enterWorker(td, night, dawn, morning){
  let result = "";
  
  if(td.getDay() == 6) {
    result += td.getFullYear() + "/" + (td.getMonth()+1) + "/" + td.getDate() + "/" + days[td.getDay()] + "\n";
    result += "야간 : " + night + "\n";
    result += "석간 : 휴무" + "\n";
    result += "조간 : " + morning;
    return result;
  }
  
  result += td.getFullYear() + "/" + (td.getMonth()+1) + "/" + td.getDate() + "/" + days[td.getDay()] + "\n";
  result += "야간 : " + night + "\n";
  result += "석간 : " + dawn + "\n";
  result += "조간 : " + morning;
  return result;
}