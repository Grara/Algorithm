const a = '김철수';
const b = "최영희";
const c = "박연진";

const days = ['일','월','화','수','목','금','토']

//기준일
const startDay = new Date(2023, 0, 30);

//날짜를 지정해서 물어볼 시 적용할 정규식
//ex) "20230204 군무"
const regex = /\d{8} 근무/;

//채팅방으로 답장
function response(room, msg, sender, isGroupChat, replier, imageDB, packageName) {

  //근무자를 알고싶은 날짜
  var targetDay;
  
  //메세지가 정확히 금일근무일 경우
  if(msg == '금일근무'){
    targetDay = new Date(); //날짜를 오늘로 설정
    replier.reply(room, getTargetDayWorkers(targetDay));
  }
  
  //메세지가 정규식을 통과한다면
  else if(regex.test(msg)){
    
    const yyyy = msg.substr(0, 4); //연도
    const mm = parseInt(msg.substr(4, 2)); //월
    const dd = msg.substr(6, 2); //일

    targetDay = new Date(yyyy,mm-1,dd); //날짜 설정
    if(mm-1 != targetDay.getMonth()){ //사용자가 입력한 월과 생성된 날짜의 월이 다르다면
      replier.reply(room, '날짜를 잘못 입력했습니다. 다시 확인해주세요.');
    }
    replier.reply(room, getTargetDayWorkers(targetDay));
  }
}

//목표날짜를 기반으로 해당 날짜와 근무자 출력
function getTargetDayWorkers(td){

  const diffTime = td.getTime() - startDay.getTime(); //목표 날짜와 기준일 사이 시간(ms)차이
  const diffDate = Math.floor(diffTime / (1000*60*60*24)); //목표 날짜와 기준일 사이 일 차이
  
  if(diffTime < 0) return "23년 1월 30일 이전은 안됨"; //기준일 이전은 안됨
  if(td.getDay() == 0) return enterWorker(td, '휴무', '휴무', '휴무'); //목표날짜가 일요일이면 모두 휴무
  if(diffTime == 0) return enterWorker(td, a, b, c); //기준일이면 패턴1
  
  const diffWeek = Math.floor(diffDate/7); //목표날짜와 기준일 사이 주 차이
  
  //근무패턴
  //3가지의 교대패턴이 주별로 로테이션 됨
  const routineType = diffWeek % 3;
  
  //패턴에 따라 다르게 출력
  switch(routineType){
    case(0):
    return enterWorker(td, a, b, c);
    case(1):
    return enterWorker(td, c, a, b);
    case(2):
    return enterWorker(td, b, c, a);
  }
}

//출력 결과값 생성
function enterWorker(td, night, dawn, morning){
  let result = "";
  
  //토요일이면 석간은 휴무
  if(td.getDay() == 6) dawn = '휴무';
  
  //해당 날짜
  result += td.getFullYear() + "/" + (td.getMonth()+1) + "/" + td.getDate() + "/" + days[td.getDay()] + "\n";
  
  result += "야간 : " + night + "\n";
  result += "석간 : " + dawn + "\n";
  result += "조간 : " + morning;
  return result;
}