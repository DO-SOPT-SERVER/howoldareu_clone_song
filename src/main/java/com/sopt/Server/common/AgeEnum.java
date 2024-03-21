package com.sopt.Server.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AgeEnum {
    TEENAGER("어리숙한 귀요미", "아직은 알아갈 것들이 너무 많은 당신! 세상엔 새로운 것들이 천지삐까리야~", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Frame%2011954.png?raw=true", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Result_ic_10.png?raw=true"),
    TWENTIES("말랑한 깜찍이", "뭐가 뭔지 이제 조금 알 것 같은 당신! 앞으로도 재밌는 것들을 많이 알아가보자", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Frame%2011951.png?raw=true","https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Result_ic_20.png?raw=true"),
    THIRTIES("성장중인 새싹이", "매일 매일 자라고 있는 당신! 다음 달엔 얼마나 발전했을지 궁금한 걸?", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Frame%2011952.png?raw=true","https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Result_ic_30.png?raw=true"),
    FORTIES("성숙한 멋쟁이", "어디에서든 멋진 당신! 뭐든 좋으니 하고 싶은 거 다 해봐!", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Frame%2011953.png?raw=true","https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Result_ic_40.png?raw=true"),
    FIFTIES("진정한 어른이", "어른이란 이런 건가 싶은 당신! 이런 식이면 진짜 어른이 될 수밖에 없어 ", "https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Frame%2011955.png?raw=true","https://github.com/DO-SOPT-SOPKATHON-iOS4/SOPKATHON-Server/blob/main/image/Result_ic_50.png?raw=true");

    private final String title;
    private final String content;
    private final String imageUrl1;
    private final String imageUrl2;

}

