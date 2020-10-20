INSERT INTO user_speak(command)
values("응 볼래") ;

INSERT INTO bot_communication (choice, conversation, user_speak_id)
values("그 사람을 __라고 불러줘", "좋아. 우선…//하트코행성 여행자 너랑 썸인지 뭔지를 타고 있는 그 분을 내가 뭐라고 부를까?",
(SELECT id from user_speak WHERE user_speak.command = "응 볼래"));

INSERT INTO user_speak(command)
values("아니 나중에") ;

INSERT INTO bot_communication (conversation, user_speak_id)
values("알겠어 하트코행성 여행자//보고 싶어지면 그때 다시 시작해보자(이모티콘)//다른 타로도 한 번 확인해봐", 
(SELECT id from user_speak WHERE user_speak.command = "아니 나중에"));


INSERT INTO user_speak(command)
values("그 사람을 __라고 불러줘") ;

INSERT INTO bot_communication (choice, conversation, user_speak_id)
values("file/.../tarot1//file/.../tarot2//file/.../tarot3", "__구나 알겠어//이미지//이제 마음을 차분하게 하고//__을 떠올리면서 카드를 한 장 뽑아보자", 
(SELECT id from user_speak WHERE user_speak.command = "그 사람을 __라고 불러줘"));


INSERT INTO tarot(id,conversation, name, choice)
values(1, "많이 좋아?//ㅇㅇㅇ//그럴 수 있어", "파랑 카드", "잘 맞을 듯//아니 거의 맞는 거 같아");

INSERT INTO tarot(id,conversation, name, choice)
values(2, "어때?//괜찮아", "초록 카드", "좋아요//싫어요");

INSERT INTO tarot(id,conversation, name, choice)
values(3, "많이 좋아?//많이 좋아하나보다//그럴 수 있어", "노랑 카드", "잘 맞아//고마워");