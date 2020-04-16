package com.nexgrid_fsp.myapplication.sharinglive;

public class WebviewScript {

    public static String getSingieDataScript(String viewStyle, int fontSize, int marginLeft, int marginRight){

        if (viewStyle.equals("v"))
        {
            return webViewScriptCode_V(fontSize,marginLeft,marginRight);
        }
        else if (viewStyle.equals("h"))
        {
            return webViewScriptCode_H(fontSize,marginLeft,marginRight);
        }
        // 오류 가 나왔을경우 임시로 데이터 넣음 : 기본 데이터 형은 V형테
        return webViewScriptCode_V(fontSize,marginLeft,marginRight);
    }

    public static String getMultiDataScript(String viewStyle, int fontSize, int marginLeft, int marginRight){

        if (viewStyle.equals("v"))
        {
            return webViewScriptCode_V(fontSize,marginLeft,marginRight);
        }
        else if (viewStyle.equals("h"))
        {
            return webViewScriptCode_H(fontSize,marginLeft,marginRight);
        }
        // 오류 가 나왔을경우 임시로 데이터 넣음 : 기본 데이터 형은 V형테
        return webViewScriptCode_V(fontSize,marginLeft,marginRight);
    }

    public static String webViewScriptCode_V(int fontSize,int marginLeft , int marginRight){

        return "<html>\n" +
                "\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "    </head>\n" +
                "\n" +
                "    <style>\n" +
                "\n" +
                "        *\n" +
                "        {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .relation\n" +
                "        {\n" +
                "            position:absolute;\n" +
                "            font-weight:bold;\n" +
                "            padding:0;\n" +
                "            line-height:0;\n" +
                "            text-align: center;\n" +
                "            white-space: pre;\n" +
                "        }\n" +
                "\n" +
                "        .name\n" +
                "        {\n" +
                "            position:absolute;\n" +
                "            font-weight:bold; \n" +
                "            padding:0; \n" +
                "            white-space: pre;\n" +
                "            word-break:break-all;\n" +
                "        }\n" +
                "\n" +
                "        .main \n" +
                "        {            \n" +
                "            \n" +
                "            margin-left:"+marginLeft+"px;"+
                "            margin-right: "+marginRight+"px;"+
                "            \n" +
                "\n" +
                "            position:relative;\n" +
                "            line-height: 0;\n" +
                "            font-size:0pt;\n" +
                "            white-space: pre;\n" +
                "            word-break: break-all;\n" +
                "        }\n" +
                "        \n" +
                "\n" +
                "        .data\n" +
                "        {\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "            line-height:0;\n" +
                "            white-space: pre;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "    <script src=\"https://code.jquery.com/jquery-3.2.1.min.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "\n" +
                "        // JSON 데이터 String Change\n" +
                "        // __data__ : 안드로이드 안에서 데이터 변경\n" +
                "         var jsonConnectionData = '__data__';\n" +
                "        \n" +
                "        //웹뷰에서 작동함\" +\n" +
                "        //LIMIT_WIDTH = screen.width;//\" +\n" +
                "        //LIMIT_HEIGHT = screen.height;//document.all ? document.body.clientHeight : window.innerHeight;\n" +
                "\n" +
                "        //웹에서 작동함\" +\n" +
                "        //LIMIT_WIDTH = document.all ? document.body.clientWidth : window.innerWidth;\n" +
                "        //LIMIT_HEIGHT = document.all ? document.body.clientHeight : window.innerHeight;\n" +
                "\n" +
                "        // sample data\n" +
                "        \n" +
                "        /*\n" +
                "            var jsonConnectionData = \n" +
                "            `\n" +
                "            {\n" +
                "                \"death_families\":[\n" +
                "                    {\n" +
                "                        \"phone\":\"01051813047\",\n" +
                "                        \"name\":\"환길동,김순자,김순자,김순자,김순자,김순자,김순자,김순자,김순자\",\n" +
                "                        \"releation\":\"배우자\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"phone\":\"01051813047\",\n" +
                "                        \"name\":\"환길동,김순자\",\n" +
                "                        \"releation\":\"배우자\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"phone\":\"01051813047\",\n" +
                "                        \"name\":\"환길동,김순자,아리랑\",\n" +
                "                        \"releation\":\"배우자\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"phone\":\"01051813047\",\n" +
                "                        \"name\":\"환길동,아리리,수리리,김순자\",\n" +
                "                        \"releation\":\"배우자\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"phone\":\"01025464949\",\n" +
                "                        \"name\":\"장남수\",\n" +
                "                        \"releation\":\"아들\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"funeralroom_id\":\"183\",\n" +
                "                \"funeralroom_name\":\"1\",\n" +
                "                \"monitor_on\":\"on\",\n" +
                "                \"death_face_image\":\"death_face_image191029인천1201993094715120.jpg\",\n" +
                "                \"death_face_image_origin\":\"191029인천1.jpg\",\n" +
                "                \"board_face_image\":\"board_face_image191029인천201993094715135.jpg\",\n" +
                "                \"board_face_image_origin\":\"191029인천.jpg\",\n" +
                "                \"death_bg_music\":\"death_bg_music피아노연주곡모음2019102024214313.mp3\",\n" +
                "                \"death_bg_music_origin\":\"피아노연주곡 모음.mp3\",\n" +
                "                \"death_name\":\"Sophi\",\n" +
                "                \"death_sex\":\"남자\",\n" +
                "                \"death_age\":\"0\",\n" +
                "                \"death_religion\":\"\",\n" +
                "                \"death_enter\":\"2019-10-24 01:10\",\n" +
                "                \"death_enter_date\":\"2019-10-24\",\n" +
                "                \"death_enter_time\":\"01:10\",\n" +
                "                \"death_exit\":\"2019-10-31 07:30\",\n" +
                "                \"death_exit_date\":\"2019-10-31\",\n" +
                "                \"death_exit_time\":\"07:30\",\n" +
                "                \"death_burrow\":\"2019-10-09 05:20\",\n" +
                "                \"death_burrow_date\":\"2019-10-09\",\n" +
                "                \"death_burrow_time\":\"05:20\",\n" +
                "                \"death_burrow_place\":\"경기도 고양시 덕양구\",\n" +
                "                \"bg_music_on\":\"off\",\n" +
                "                \"death_use_ribbon\":\"N\",\n" +
                "                \"death_ribbon_detail\":\"\",\n" +
                "                \"board_template\":\"블랙\",\n" +
                "                \"board_use_bg\":\"Y\",\n" +
                "                \"board_bg\":\"국기\",\n" +
                "                \"board_bg_detail\":\"국기_2\",\n" +
                "                \"death_id\":\"160\",\n" +
                "                \"death_families_name\":[\n" +
                "                \"환길동\",\n" +
                "                \"장남수\"\n" +
                "                ],\n" +
                "                \"death_families_phone\":[\n" +
                "                \"01051813047\",\n" +
                "                \"01025464949\"\n" +
                "                ],\n" +
                "                \"death_families_releation\":[\n" +
                "                \"배우자\",\n" +
                "                \"아들\"\n" +
                "                ]\n" +
                "                }\n" +
                "            `;\n" +
                "        */\n" +
                "                \n" +
                "        // JSON 데이터 \n" +
                "        var  jsonDataList = JSON.parse(jsonConnectionData);\n" +
                "\n" +
                "        // body 에서 호출\n" +
                "        function mainBodyLayout()\n" +
                "        {\n" +
                "            // webfontSizeew 가로 사이즈\n" +
                "            const LIMIT_WIDTH            = document.body.offsetWidth;\n" +
                "            \n" +
                "            // webfontSizeew 세로 사이즈\n" +
                "            const LIMIT_HEIGHT           = document.body.offsetHeight;\n" +
                "\n" +
                "            // 데이터 X축 시작 위치\n" +
                "            const START_X                = 0;\n" +
                "\n" +
                "            // 상주 관계 영역 사이 간격\n" +
                "            const RELATION_AREA_DISTANCE = 2;\n" +
                "\n" +
                "            // 상주 이름 영역 사이 간격\n" +
                "            const NAME_AREA_DISTANCE     = 2;\n" +
                "\n" +
                "            // 자동으로 라인수를 조절하는 모드\n" +
                "            const AUTO_LINE_MODE = false;\n" +
                "\n" +
                "            // 자동으로 설정된 값으로 조절하는 라인 수(AUTO_LINE_MODE가 true여야 동작)\n" +
                "            const AUTO_LINE_NUMBER = 1;\n" +
                "\n" +
                "            // Body 중간 정렬 \n" +
                "            const DATA_AREA_CENTER_MODE = true;\n" +
                "\n" +
                "            // 시작 폰트 사이즈 (초기값 : 최대 폰트사이즈)\n" +
                "            var fontSize =" + fontSize+";"+
                "\n" +
                "            // 폰트 조절 변수 (폰트 사이즈가 화면을 넘을 경우 초기값 1씩 줄인다)\n" +
                "            var ctlFontSize = 1;\n" +
                "\n" +
                "            // 데이터의 각각의 높이를 임시로 저장 및 할당하는 변수\n" +
                "            var tmpHeight = 0;\n" +
                "\n" +
                "            // 데이터의 각각의 넓이를 임시로 저장 및 할당하는 변수\n" +
                "            var tmpWidth  = 0;\n" +
                "\n" +
                "            // 데이터를 임시 저장하는 변수\n" +
                "            var tmpTxt    = '';\n" +
                "\n" +
                "            // 표시되는 데이터의 최대 가로 길이\n" +
                "            var dataMaxWidth = 0;\n" +
                "\n" +
                "            // all data object\n" +
                "            // makedData[index] -> relation\n" +
                "            //                  -> names[index]    -> name\n" +
                "            // ex.)\n" +
                "            // 배우자 : 환길동,김순자,김순자\n" +
                "            //          김순자\n" +
                "            //          김순자,김순자\n" +
                "            // 아들   : 장남수\n" +
                "            var makedData  = [];\n" +
                "\n" +
                "            // hmwoo\n" +
                "            var formatData = [];\n" +
                "\n" +
                "            // add all data\n" +
                "            for (var i = 0; i < jsonDataList.death_families.length; ++i) \n" +
                "            {\n" +
                "\n" +
                "                // 상주 관계 \n" +
                "                // ex.) 배우자\n" +
                "                var releation = jsonDataList.death_families[i].releation;\n" +
                "\n" +
                "                // 상주 이름\n" +
                "                // ex.) 환길동,김순자,김순자\n" +
                "                var name = jsonDataList.death_families[i].name;\n" +
                "\n" +
                "                // 상주 관계를 나타내는 index를 unique 하게 하기 위해 사용하는 임시 변수\n" +
                "                var findedIndex = -1;\n" +
                "                \n" +
                "                // 상주 관계에 따른 value(이름)을 넣어주기위해 index 를 검색\n" +
                "                for (var j = 0; j < makedData.length; ++j) \n" +
                "                {\n" +
                "                    // 해당하는 상주 관계(relation)를 발견하였을 경우 임시 변수에 index를 저장\n" +
                "                    if (makedData[j].releation == releation) \n" +
                "                    {    \n" +
                "                        findedIndex =  j;\n" +
                "                        break;\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // 위 루프 문에서 index를 검색하지 못하였을 경우 상주 관계를 새로 추가\n" +
                "                if (findedIndex < 0) \n" +
                "                {    \n" +
                "                    findedIndex = makedData.length;\n" +
                "\n" +
                "                    // 새로운 상주 관계를 저장\n" +
                "                    makedData.push({ releation: releation, names: [] });\n" +
                "                    \n" +
                "                }    \n" +
                "            \n" +
                "                // 상주 관계에 따른 이름을 추가\n" +
                "                makedData[findedIndex].names.push(name);\n" +
                "            }\n" +
                "\n" +
                "            // fontSize control\n" +
                "            for (fontSize; fontSize >= 1; fontSize = fontSize - ctlFontSize)\n" +
                "            {\n" +
                "                formatData = [];\n" +
                "\n" +
                "                tmpHeight = 0;\n" +
                "\n" +
                "                dataMaxWidth = 0;\n" +
                "\n" +
                "                // 상주 관계 영역 가로 길이\n" +
                "                var relationAreaWidth = 0;\n" +
                "\n" +
                "                // 상주 관계 영역 세로 길이\n" +
                "                var relationAreaHeight = 0;\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역 가로 길이\n" +
                "                var dataDivAreaWidth = 0;\n" +
                "\n" +
                "                // css 폰트 사이즈 적용\n" +
                "                $(\"#spanStrWidth\").css({ \"font-size\": fontSize + \"pt\" });\n" +
                "\n" +
                "                // css 폴즈체 사이즈\n" +
                "                $(\"#spanStrWidth\").css({ \"font-weight\" :\"bold\" });\n" +
                "                \n" +
                "                // 상주 관계 영역 사이즈 적용\n" +
                "                for (var i = 0; i < makedData.length; ++i) \n" +
                "                {\n" +
                "                    // 임시로 상주 관계 텍스트 삽입\n" +
                "                    $(\"#spanStrWidth\").text(makedData[i].releation);\n" +
                "\n" +
                "                    // 상주 관계 가로 영역 계산\n" +
                "                    if (relationAreaWidth < $(\"#spanStrWidth\").outerWidth()) \n" +
                "                    {\n" +
                "                        relationAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                        relationAreaHeight = $(\"#spanStrWidth\").outerHeight();\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // postion의 경우 글자에 top 0 을 지정하면 글자의 가운데에서부터 영역을 잡는 버그가 있으므로 글자의 가운데 크기 만큼 더해준다.\n" +
                "                tmpHeight += relationAreaHeight / 2;\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역\n" +
                "                $(\"#spanStrWidth\").text(' : ');\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역 가로 길이 적용\n" +
                "                dataDivAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                for (var i = 0; i < makedData.length; ++i) \n" +
                "                {\n" +
                "                    tmpWidth = 0;\n" +
                "\n" +
                "                    formatData.push({ type: 'relation', x: START_X, y: tmpHeight, width: relationAreaWidth, fontSize: fontSize, text: makedData[i].releation });\n" +
                "\n" +
                "                    tmpWidth += relationAreaWidth;\n" +
                "                    \n" +
                "                    formatData.push({ type: 'split', x: tmpWidth, y: tmpHeight, width: dataDivAreaWidth, fontSize: fontSize, text: ' : ' });\n" +
                "\n" +
                "                    tmpWidth += dataDivAreaWidth;\n" +
                "                    \n" +
                "                    var nameAreaWidth = 0;\n" +
                "\n" +
                "                    for (var j = 0; j < makedData[i].names.length; ++j) \n" +
                "                    {\n" +
                "                        tmpTxt = makedData[i].names[j];\n" +
                "                        \n" +
                "                        if(AUTO_LINE_MODE == true)\n" +
                "                        {\n" +
                "                            if(j != 0)\n" +
                "                            {\n" +
                "                                tmpTxt = \",\" + tmpTxt;\n" +
                "                            }\n" +
                "                        }\n" +
                "\n" +
                "                        $(\"#spanStrWidth\").text(tmpTxt);\n" +
                "                        \n" +
                "                        var nameAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                        formatData.push({ type: 'name', x: tmpWidth, y: tmpHeight, fontSize: fontSize, text: tmpTxt });\n" +
                "\n" +
                "                        if(AUTO_LINE_MODE)\n" +
                "                        {\n" +
                "                            tmpWidth += nameAreaWidth;\n" +
                "\n" +
                "                            if(dataMaxWidth < tmpWidth)\n" +
                "                            {\n" +
                "                                dataMaxWidth = tmpWidth;\n" +
                "                            }\n" +
                "\n" +
                "                            if(j == makedData[i].names.length -1)\n" +
                "                            {\n" +
                "                                tmpHeight += $(\"#spanStrWidth\").outerHeight();\n" +
                "                            }\n" +
                "                        }\n" +
                "                        else                        \n" +
                "                        {\n" +
                "                            if(dataMaxWidth < tmpWidth + nameAreaWidth)\n" +
                "                            {\n" +
                "                                dataMaxWidth = tmpWidth + nameAreaWidth;\n" +
                "                            }\n" +
                "\n" +
                "                            tmpHeight += $(\"#spanStrWidth\").outerHeight();\n" +
                "\n" +
                "                            if((i + j) != (makedData.length + makedData[i].names.length - 2))\n" +
                "                            {\n" +
                "                                tmpHeight += NAME_AREA_DISTANCE;\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                if((tmpHeight < LIMIT_HEIGHT && dataMaxWidth < LIMIT_WIDTH) || fontSize <= 1)\n" +
                "                {\n" +
                "                    var innerhtml = \"\";\n" +
                "\n" +
                "                    for (var i = 0; i < formatData.length; ++i) \n" +
                "                    {\n" +
                "                        // 상주관계일 경우\n" +
                "                        if (formatData[i].type == 'relation') \n" +
                "                        {\n" +
                "                            innerhtml += '<p class=\"relation\" style=\"top:' + formatData[i].y +'; width: ' + formatData[i].width + ';  font-size:' + formatData[i].fontSize + 'pt;\">' +formatData[i].text +'</p>';\n" +
                "                        } \n" +
                "                        // 상주이름일 경우\n" +
                "                        else \n" +
                "                        {   \n" +
                "                            innerhtml += '<p class=\"name\" style=\"top:' + (formatData[i].y) +'; left:' + formatData[i].x+'; font-size:' + formatData[i].fontSize + 'pt;\">' + formatData[i].text + '</p>';\n" +
                "                        }\n" +
                "                    }\n" +
                "\n" +
                "                    if(DATA_AREA_CENTER_MODE)\n" +
                "                    {\n" +
                "                        $(\"#mainBody\").css({ \"transform\":\"translate(0, -50%)\"});\n" +
                "                        $(\"#mainBody\").css({ \"top\":\"50%\"});    \n" +
                "                        $(\"#mainBody\").css({ \"width\":dataMaxWidth + \"px\"});\n" +
                "                        $(\"#mainBody\").css({ \"height\":tmpHeight + \"px\"});\n" +
                "                    }\n" +
                "\n" +
                "                    // html 적용\n" +
                "                    $(\"#mainBody\").html(innerhtml);\n" +
                "                    break;\n" +
                "                }\n" +
                "\n" +
                "                if(LIMIT_WIDTH - dataMaxWidth < 50)\n" +
                "                {\n" +
                "                    ctlFontSize = 0.5;\n" +
                "                }\n" +
                "            }\n" +
                "                \n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "    <body id=\"mainBody\" class=\"main\" onload=\"mainBodyLayout()\"></body>\n" +
                "\n" +
                "    <span id=\"spanStrWidth\" class=\"data\"></span>\n" +
                "\n" +
                "</html>" ;

    }

    public static String webViewScriptCode_H(int fontSize,int marginLeft , int marginRight){

        return "" +
                "<html>\n" +
                "\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, user-scalable=no\">\n" +
                "    </head>\n" +
                "\n" +
                "    <style>\n" +
                "\n" +
                "        *\n" +
                "        {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .relation\n" +
                "        {\n" +
                "            position:absolute;\n" +
                "            font-weight:bold;\n" +
                "            padding:0;\n" +
                "            line-height:0;\n" +
                "            text-align: center;\n" +
                "            white-space: pre;\n" +
                "        }\n" +
                "\n" +
                "        .name\n" +
                "        {\n" +
                "            position:absolute;\n" +
                "            font-weight:bold; \n" +
                "            padding:0; \n" +
                "            white-space: pre;\n" +
                "            word-break:break-all;\n" +
                "        }\n" +
                "\n" +
                "        .main \n" +
                "        {            \n" +
                "            /*\n" +
                "                margin:40px;    \n" +
                "            */\n" +
                "\n" +
                "            position:relative;\n" +
                "            line-height: 0;\n" +
                "            font-size:0pt;\n" +
                "            white-space: pre;\n" +
                "            word-break: break-all;\n" +
                "        }\n" +
                "        \n" +
                "\n" +
                "        .data\n" +
                "        {\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "            line-height:0;\n" +
                "            white-space: pre;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "    <script src=\"https://code.jquery.com/jquery-3.2.1.min.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "\n" +
                "        // JSON 데이터 String Change\n" +
                "        // __data__ : 안드로이드 안에서 데이터 변경\n" +
                "        var jsonConnectionData = '__data__';\n" +
                "        \n" +
                "        //웹뷰에서 작동함\" +\n" +
                "        //LIMIT_WIDTH = screen.width;//\" +\n" +
                "        //LIMIT_HEIGHT = screen.height;//document.all ? document.body.clientHeight : window.innerHeight;\n" +
                "\n" +
                "        //웹에서 작동함\" +\n" +
                "        //LIMIT_WIDTH = document.all ? document.body.clientWidth : window.innerWidth;\n" +
                "        //LIMIT_HEIGHT = document.all ? document.body.clientHeight : window.innerHeight;\n" +
                "\n" +
                "        // sample data\n" +
                "        \n" +
                "        /*\n" +
                "        var jsonConnectionData = \n" +
                "        `\n" +
                "        {\n" +
                "            \"death_families\":[\n" +
                "                {\n" +
                "                    \"phone\":\"01051813047\",\n" +
                "                    \"name\":\"환길동,김순자,김순자,김순자,김순자,김순자,김순자,김순자,김순자\",\n" +
                "                    \"releation\":\"배우자\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"phone\":\"01051813047\",\n" +
                "                    \"name\":\"환길동,김순자\",\n" +
                "                    \"releation\":\"배우자\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"phone\":\"01051813047\",\n" +
                "                    \"name\":\"환길동,김순자,아리랑\",\n" +
                "                    \"releation\":\"배우자\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"phone\":\"01051813047\",\n" +
                "                    \"name\":\"환길동,아리리,수리리,김순자\",\n" +
                "                    \"releation\":\"배우자\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"phone\":\"01025464949\",\n" +
                "                    \"name\":\"장남수\",\n" +
                "                    \"releation\":\"아들\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"funeralroom_id\":\"183\",\n" +
                "            \"funeralroom_name\":\"1\",\n" +
                "            \"monitor_on\":\"on\",\n" +
                "            \"death_face_image\":\"death_face_image191029인천1201993094715120.jpg\",\n" +
                "            \"death_face_image_origin\":\"191029인천1.jpg\",\n" +
                "            \"board_face_image\":\"board_face_image191029인천201993094715135.jpg\",\n" +
                "            \"board_face_image_origin\":\"191029인천.jpg\",\n" +
                "            \"death_bg_music\":\"death_bg_music피아노연주곡모음2019102024214313.mp3\",\n" +
                "            \"death_bg_music_origin\":\"피아노연주곡 모음.mp3\",\n" +
                "            \"death_name\":\"Sophi\",\n" +
                "            \"death_sex\":\"남자\",\n" +
                "            \"death_age\":\"0\",\n" +
                "            \"death_religion\":\"\",\n" +
                "            \"death_enter\":\"2019-10-24 01:10\",\n" +
                "            \"death_enter_date\":\"2019-10-24\",\n" +
                "            \"death_enter_time\":\"01:10\",\n" +
                "            \"death_exit\":\"2019-10-31 07:30\",\n" +
                "            \"death_exit_date\":\"2019-10-31\",\n" +
                "            \"death_exit_time\":\"07:30\",\n" +
                "            \"death_burrow\":\"2019-10-09 05:20\",\n" +
                "            \"death_burrow_date\":\"2019-10-09\",\n" +
                "            \"death_burrow_time\":\"05:20\",\n" +
                "            \"death_burrow_place\":\"경기도 고양시 덕양구\",\n" +
                "            \"bg_music_on\":\"off\",\n" +
                "            \"death_use_ribbon\":\"N\",\n" +
                "            \"death_ribbon_detail\":\"\",\n" +
                "            \"board_template\":\"블랙\",\n" +
                "            \"board_use_bg\":\"Y\",\n" +
                "            \"board_bg\":\"국기\",\n" +
                "            \"board_bg_detail\":\"국기_2\",\n" +
                "            \"death_id\":\"160\",\n" +
                "            \"death_families_name\":[\n" +
                "            \"환길동\",\n" +
                "            \"장남수\"\n" +
                "            ],\n" +
                "            \"death_families_phone\":[\n" +
                "            \"01051813047\",\n" +
                "            \"01025464949\"\n" +
                "            ],\n" +
                "            \"death_families_releation\":[\n" +
                "            \"배우자\",\n" +
                "            \"아들\"\n" +
                "            ]\n" +
                "            }\n" +
                "        `;\n" +
                "        */\n" +
                "                \n" +
                "        // JSON 데이터 \n" +
                "        var  jsonDataList = JSON.parse(jsonConnectionData);\n" +
                "\n" +
                "        // body 에서 호출\n" +
                "        function mainBodyLayout()\n" +
                "        {\n" +
                "            // webfontSizeew 가로 사이즈\n" +
                "            const LIMIT_WIDTH            = window.outerWidth;\n" +
                "            \n" +
                "            // webfontSizeew 세로 사이즈\n" +
                "            const LIMIT_HEIGHT           = window.outerHeight\n" +
                "\n" +
                "            // 데이터 X축 시작 위치\n" +
                "            const START_X                = 0;\n" +
                "\n" +
                "            // 상주 관계 영역 사이 간격\n" +
                "            const RELATION_AREA_DISTANCE = 2;\n" +
                "\n" +
                "            // 상주 이름 영역 사이 간격\n" +
                "            const NAME_AREA_DISTANCE     = 2;\n" +
                "\n" +
                "            // 자동으로 라인수를 조절하는 모드\n" +
                "            const AUTO_LINE_MODE = false;\n" +
                "\n" +
                "            // 자동으로 설정된 값으로 조절하는 라인 수(AUTO_LINE_MODE가 true여야 동작)\n" +
                "            const AUTO_LINE_NUMBER = 1;\n" +
                "\n" +
                "            // Body 중간 정렬 \n" +
                "            const DATA_AREA_CENTER_MODE = true;\n" +
                "\n" +
                "            // 시작 폰트 사이즈 (초기값 : 최대 폰트사이즈)\n" +
                "            var fontSize = " + fontSize +";"+
                "\n" +
                "            // 폰트 조절 변수 (폰트 사이즈가 화면을 넘을 경우 초기값 1씩 줄인다)\n" +
                "            var ctlFontSize = 1;\n" +
                "\n" +
                "            // 데이터의 각각의 높이를 임시로 저장 및 할당하는 변수\n" +
                "            var tmpHeight = 0;\n" +
                "\n" +
                "            // 데이터의 각각의 넓이를 임시로 저장 및 할당하는 변수\n" +
                "            var tmpWidth  = 0;\n" +
                "\n" +
                "            // 데이터를 임시 저장하는 변수\n" +
                "            var tmpTxt    = '';\n" +
                "\n" +
                "            // 표시되는 데이터의 최대 가로 길이\n" +
                "            var dataMaxWidth = 0;\n" +
                "\n" +
                "            // all data object\n" +
                "            // makedData[index] -> relation\n" +
                "            //                  -> names[index]    -> name\n" +
                "            // ex.)\n" +
                "            // 배우자 : 환길동,김순자,김순자\n" +
                "            //          김순자\n" +
                "            //          김순자,김순자\n" +
                "            // 아들   : 장남수\n" +
                "            var makedData  = [];\n" +
                "\n" +
                "            // hmwoo\n" +
                "            var formatData = [];\n" +
                "\n" +
                "            // add all data\n" +
                "            for (var i = 0; i < jsonDataList.death_families.length; ++i) \n" +
                "            {\n" +
                "\n" +
                "                // 상주 관계 \n" +
                "                // ex.) 배우자\n" +
                "                var releation = jsonDataList.death_families[i].releation;\n" +
                "\n" +
                "                // 상주 이름\n" +
                "                // ex.) 환길동,김순자,김순자\n" +
                "                var name = jsonDataList.death_families[i].name;\n" +
                "\n" +
                "                // 상주 관계를 나타내는 index를 unique 하게 하기 위해 사용하는 임시 변수\n" +
                "                var findedIndex = -1;\n" +
                "                \n" +
                "                // 상주 관계에 따른 value(이름)을 넣어주기위해 index 를 검색\n" +
                "                for (var j = 0; j < makedData.length; ++j) \n" +
                "                {\n" +
                "                    // 해당하는 상주 관계(relation)를 발견하였을 경우 임시 변수에 index를 저장\n" +
                "                    if (makedData[j].releation == releation) \n" +
                "                    {    \n" +
                "                        findedIndex =  j;\n" +
                "                        break;\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // 위 루프 문에서 index를 검색하지 못하였을 경우 상주 관계를 새로 추가\n" +
                "                if (findedIndex < 0) \n" +
                "                {    \n" +
                "                    findedIndex = makedData.length;\n" +
                "\n" +
                "                    // 새로운 상주 관계를 저장\n" +
                "                    makedData.push({ releation: releation, names: [] });\n" +
                "                    \n" +
                "                }    \n" +
                "            \n" +
                "                // 상주 관계에 따른 이름을 추가\n" +
                "                makedData[findedIndex].names.push(name);\n" +
                "            }\n" +
                "\n" +
                "            // fontSize control\n" +
                "            for (fontSize; fontSize >= 1; fontSize = fontSize - ctlFontSize)\n" +
                "            {\n" +
                "                formatData = [];\n" +
                "\n" +
                "                tmpHeight = 0;\n" +
                "\n" +
                "                dataMaxWidth = 0;\n" +
                "\n" +
                "                // 상주 관계 영역 가로 길이\n" +
                "                var relationAreaWidth = 0;\n" +
                "\n" +
                "                // 상주 관계 영역 세로 길이\n" +
                "                var relationAreaHeight = 0;\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역 가로 길이\n" +
                "                var dataDivAreaWidth = 0;\n" +
                "\n" +
                "                // css 폰트 사이즈 적용\n" +
                "                $(\"#spanStrWidth\").css({ \"font-size\": fontSize + \"pt\" });\n" +
                "\n" +
                "                // css 폴즈체 사이즈\n" +
                "                $(\"#spanStrWidth\").css({ \"font-weight\" :\"bold\" });\n" +
                "                \n" +
                "                // 상주 관계 영역 사이즈 적용\n" +
                "                for (var i = 0; i < makedData.length; ++i) \n" +
                "                {\n" +
                "                    // 임시로 상주 관계 텍스트 삽입\n" +
                "                    $(\"#spanStrWidth\").text(makedData[i].releation);\n" +
                "\n" +
                "                    // 상주 관계 가로 영역 계산\n" +
                "                    if (relationAreaWidth < $(\"#spanStrWidth\").outerWidth()) \n" +
                "                    {\n" +
                "                        relationAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                        relationAreaHeight = $(\"#spanStrWidth\").outerHeight();\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                // postion의 경우 글자에 top 0 을 지정하면 글자의 가운데에서부터 영역을 잡는 버그가 있으므로 글자의 가운데 크기 만큼 더해준다.\n" +
                "                tmpHeight += relationAreaHeight / 2;\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역\n" +
                "                $(\"#spanStrWidth\").text(' : ');\n" +
                "\n" +
                "                // 상주 관계, 상주 이름 분할 영역 가로 길이 적용\n" +
                "                dataDivAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                for (var i = 0; i < makedData.length; ++i) \n" +
                "                {\n" +
                "                    tmpWidth = 0;\n" +
                "\n" +
                "                    formatData.push({ type: 'relation', x: START_X, y: tmpHeight, width: relationAreaWidth, fontSize: fontSize, text: makedData[i].releation });\n" +
                "\n" +
                "                    tmpWidth += relationAreaWidth;\n" +
                "                    \n" +
                "                    formatData.push({ type: 'split', x: tmpWidth, y: tmpHeight, width: dataDivAreaWidth, fontSize: fontSize, text: ' : ' });\n" +
                "\n" +
                "                    tmpWidth += dataDivAreaWidth;\n" +
                "                    \n" +
                "                    var nameAreaWidth = 0;\n" +
                "\n" +
                "                    for (var j = 0; j < makedData[i].names.length; ++j) \n" +
                "                    {\n" +
                "                        tmpTxt = makedData[i].names[j];\n" +
                "                        \n" +
                "                        if(AUTO_LINE_MODE == true)\n" +
                "                        {\n" +
                "                            if(j != 0)\n" +
                "                            {\n" +
                "                                tmpTxt = \",\" + tmpTxt;\n" +
                "                            }\n" +
                "                        }\n" +
                "\n" +
                "                        $(\"#spanStrWidth\").text(tmpTxt);\n" +
                "                        \n" +
                "                        var nameAreaWidth = $(\"#spanStrWidth\").outerWidth();\n" +
                "\n" +
                "                        formatData.push({ type: 'name', x: tmpWidth, y: tmpHeight, fontSize: fontSize, text: tmpTxt });\n" +
                "\n" +
                "                        if(AUTO_LINE_MODE)\n" +
                "                        {\n" +
                "                            tmpWidth += nameAreaWidth;\n" +
                "\n" +
                "                            if(dataMaxWidth < tmpWidth)\n" +
                "                            {\n" +
                "                                dataMaxWidth = tmpWidth;\n" +
                "                            }\n" +
                "\n" +
                "                            if(j == makedData[i].names.length -1)\n" +
                "                            {\n" +
                "                                tmpHeight += $(\"#spanStrWidth\").outerHeight();\n" +
                "                            }\n" +
                "                        }\n" +
                "                        else                        \n" +
                "                        {\n" +
                "                            if(dataMaxWidth < tmpWidth + nameAreaWidth)\n" +
                "                            {\n" +
                "                                dataMaxWidth = tmpWidth + nameAreaWidth;\n" +
                "                            }\n" +
                "\n" +
                "                            tmpHeight += $(\"#spanStrWidth\").outerHeight();\n" +
                "\n" +
                "                            if((i + j) != (makedData.length + makedData[i].names.length - 2))\n" +
                "                            {\n" +
                "                                tmpHeight += NAME_AREA_DISTANCE;\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                if((tmpHeight < LIMIT_HEIGHT && dataMaxWidth < LIMIT_WIDTH) || fontSize <= 1)\n" +
                "                {\n" +
                "                    var innerhtml = \"\";\n" +
                "\n" +
                "                    for (var i = 0; i < formatData.length; ++i) \n" +
                "                    {\n" +
                "                        // 상주관계일 경우\n" +
                "                        if (formatData[i].type == 'relation') \n" +
                "                        {\n" +
                "                            innerhtml += '<p class=\"relation\" style=\"top:' + formatData[i].y +'; width: ' + formatData[i].width + ';  font-size:' + formatData[i].fontSize + 'pt;\">' +formatData[i].text +'</p>';\n" +
                "                        } \n" +
                "                        // 상주이름일 경우\n" +
                "                        else \n" +
                "                        {   \n" +
                "                            innerhtml += '<p class=\"name\" style=\"top:' + (formatData[i].y) +'; left:' + formatData[i].x+'; font-size:' + formatData[i].fontSize + 'pt;\">' + formatData[i].text + '</p>';\n" +
                "                        }\n" +
                "                    }\n" +
                "\n" +
                "                    if(DATA_AREA_CENTER_MODE)\n" +
                "                    {\n" +
                "                        $(\"#mainBody\").css({ \"transform\":\"translate(0, -50%)\"});\n" +
                "                        $(\"#mainBody\").css({ \"top\":\"50%\"});    \n" +
                "                        $(\"#mainBody\").css({ \"width\":dataMaxWidth + \"px\"});\n" +
                "                        $(\"#mainBody\").css({ \"height\":tmpHeight + \"px\"});\n" +
                "                    }\n" +
                "\n" +
                "                    if(fontSize <= 1)\n" +
                "                    {\n" +
                "                        var rate = Math.floor((LIMIT_WIDTH / dataMaxWidth) * 100) / 100;\n" +
                "                        \n" +
                "                        if(DATA_AREA_CENTER_MODE)\n" +
                "                        {\n" +
                "                            $(\"#mainBody\").css({ \"transform\":\"translate(0, -50%) scale(\" + rate + \")\"});\n" +
                "                            $(\"#mainBody\").css({ \"transform-origin\":\"0 0\"});\n" +
                "                        }\n" +
                "                        else\n" +
                "                        {\n" +
                "                            $(\"#mainBody\").css({ \"transform\":\"scale(:\" + rate + \")\"});\n" +
                "                            $(\"#mainBody\").css({ \"transform-origin\":\"0 0\"});\n" +
                "                        }\n" +
                "                    }\n" +
                "\n" +
                "                    // html 적용\n" +
                "                    $(\"#mainBody\").html(innerhtml);\n" +
                "                    break;\n" +
                "                }\n" +
                "\n" +
                "                if(LIMIT_WIDTH - dataMaxWidth < 50)\n" +
                "                {\n" +
                "                    ctlFontSize = 0.5;\n" +
                "                }\n" +
                "            }\n" +
                "                \n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "    <body id=\"mainBody\" class=\"main\" onload=\"mainBodyLayout()\"></body>\n" +
                "\n" +
                "    <span id=\"spanStrWidth\" class=\"data\"></span>\n" +
                "\n" +
                "</html>" ;

    }
}
