function connect() {
  // pub/sub event
  ws.connect({}, function(frame) {
      ws.subscribe("/topic/sample/"+vm.$data.roomId, function(message) {
          var recv = JSON.parse(message.body);
          vm.recvMessage(recv);
      }, {
        'senderEmail': 'nmj18@naver.com'
      });
      ws.send("/app/chat/message", {}, JSON.stringify({type:'ENTER', roomId:vm.$data.roomId, sender:vm.$data.sender}));
  }, function(error) {
      if(reconnect++ <= 5) {
          setTimeout(function() {
              console.log("connection reconnect");
              sock = new SockJS("/ws/chat");
              ws = Stomp.over(sock);
              connect();
          },10*1000);
      }
  });
}

stompClient.subscribe('/topic/foo', function(message) {
  console.log('Received message:', message.body);
}, {
  'header1': 'override'
});