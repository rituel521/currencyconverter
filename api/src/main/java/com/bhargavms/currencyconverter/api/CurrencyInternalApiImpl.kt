package com.bhargavms.currencyconverter.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder

internal class CurrencyInternalApiImpl : CurrencyInternalApi {
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    override suspend fun liveRates(source: String): LiveRatesResponse {
        return gson.fromJson(
            "{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"timestamp\":1571760547,\"source\":\"USD\",\"quotes\":{\"USDAED\":3.672971,\"USDAFN\":78.250364,\"USDALL\":110.796433,\"USDAMD\":475.9601,\"USDANG\":1.755165,\"USDAOA\":451.576498,\"USDARS\":58.660699,\"USDAUD\":1.457105,\"USDAWG\":1.8,\"USDAZN\":1.699023,\"USDBAM\":1.75554,\"USDBBD\":2.019315,\"USDBDT\":84.70219,\"USDBGN\":1.755734,\"USDBHD\":0.376984,\"USDBIF\":1871,\"USDBMD\":1,\"USDBND\":1.362306,\"USDBOB\":6.915558,\"USDBRL\":4.073199,\"USDBSD\":1.000054,\"USDBTC\":0.000122,\"USDBTN\":71.00104,\"USDBWP\":10.835352,\"USDBYN\":2.037277,\"USDBYR\":19600,\"USDBZD\":2.015905,\"USDCAD\":1.308445,\"USDCDF\":1659.999687,\"USDCHF\":0.989045,\"USDCLF\":0.02626,\"USDCLP\":724.416238,\"USDCNY\":7.077021,\"USDCOP\":1897.5,\"USDCRC\":582.33256,\"USDCUC\":1,\"USDCUP\":26.5,\"USDCVE\":99.375036,\"USDCZK\":22.950702,\"USDDJF\":177.719844,\"USDDKK\":6.707385,\"USDDOP\":52.78421,\"USDDZD\":119.455017,\"USDEGP\":16.200097,\"USDERN\":14.999878,\"USDETB\":29.685792,\"USDEUR\":0.897797,\"USDFJD\":2.195957,\"USDFKP\":0.81288,\"USDGBP\":0.772155,\"USDGEL\":2.964983,\"USDGGP\":0.772218,\"USDGHS\":5.469917,\"USDGIP\":0.81288,\"USDGMD\":50.803293,\"USDGNF\":9230.000236,\"USDGTQ\":7.765631,\"USDGYD\":208.65421,\"USDHKD\":7.84285,\"USDHNL\":24.740591,\"USDHRK\":6.6788,\"USDHTG\":96.04089,\"USDHUF\":295.366035,\"USDIDR\":14019,\"USDILS\":3.533302,\"USDIMP\":0.772218,\"USDINR\":70.787505,\"USDIQD\":1189.5,\"USDIRR\":42104.999792,\"USDISK\":124.829942,\"USDJEP\":0.772218,\"USDJMD\":138.011381,\"USDJOD\":0.708979,\"USDJPY\":108.57398,\"USDKES\":103.59657,\"USDKGS\":69.811397,\"USDKHR\":4060.000154,\"USDKMF\":442.299493,\"USDKPW\":900.065913,\"USDKRW\":1172.57049,\"USDKWD\":0.30345,\"USDKYD\":0.833378,\"USDKZT\":390.08693,\"USDLAK\":8824.999871,\"USDLBP\":1513.000139,\"USDLKR\":181.51552,\"USDLRD\":211.504996,\"USDLSL\":14.659723,\"USDLTL\":2.95274,\"USDLVL\":0.60489,\"USDLYD\":1.415027,\"USDMAD\":9.605026,\"USDMDL\":17.369859,\"USDMGA\":3625.000068,\"USDMKD\":55.237624,\"USDMMK\":1534.630901,\"USDMNT\":2688.03097,\"USDMOP\":8.078966,\"USDMRO\":357.000346,\"USDMUR\":36.320873,\"USDMVR\":15.409708,\"USDMWK\":729.999795,\"USDMXN\":19.099103,\"USDMYR\":4.188503,\"USDMZN\":62.375011,\"USDNAD\":14.710047,\"USDNGN\":362.000273,\"USDNIO\":33.650316,\"USDNOK\":9.140595,\"USDNPR\":113.60152,\"USDNZD\":1.56055,\"USDOMR\":0.385127,\"USDPAB\":1.000054,\"USDPEN\":3.338498,\"USDPGK\":3.379739,\"USDPHP\":51.1855,\"USDPKR\":156.150592,\"USDPLN\":3.83511,\"USDPYG\":6448.561199,\"USDQAR\":3.64075,\"USDRON\":4.274604,\"USDRSD\":105.550247,\"USDRUB\":63.646203,\"USDRWF\":926,\"USDSAR\":3.750718,\"USDSBD\":8.294408,\"USDSCR\":13.704927,\"USDSDG\":45.118347,\"USDSEK\":9.63464,\"USDSGD\":1.362402,\"USDSHP\":1.320899,\"USDSLL\":9600.000109,\"USDSOS\":580.999378,\"USDSRD\":7.45796,\"USDSTD\":21560.79,\"USDSVC\":8.750247,\"USDSYP\":514.99995,\"USDSZL\":14.660011,\"USDTHB\":30.290828,\"USDTJS\":9.691271,\"USDTMT\":3.5,\"USDTND\":2.830501,\"USDTOP\":2.31355,\"USDTRY\":5.81079,\"USDTTD\":6.772578,\"USDTWD\":30.586497,\"USDTZS\":2296.09568,\"USDUAH\":24.892894,\"USDUGX\":3695.349695,\"USDUSD\":1,\"USDUYU\":37.378606,\"USDUZS\":9447.999703,\"USDVEF\":9.987503,\"USDVND\":23208.5,\"USDVUV\":116.701867,\"USDWST\":2.68383,\"USDXAF\":588.75635,\"USDXAG\":0.05728,\"USDXAU\":0.000674,\"USDXCD\":2.70255,\"USDXDR\":0.725983,\"USDXOF\":587.999793,\"USDXPF\":107.449856,\"USDYER\":250.348038,\"USDZAR\":14.614198,\"USDZMK\":9001.203315,\"USDZMW\":13.233615,\"USDZWL\":322.000001}}",
            LiveRatesResponse::class.java
        )
    }

    override suspend fun listSupportedCurrencies(): SupportedCurrenciesResponse {
        return gson.fromJson(
            "{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\"," +
                    "\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"currencies\":{\"AED\":\"" +
                    "United Arab Emirates Dirham\",\"AFN\":\"Afghan Afghani\",\"ALL\":\"Albanian Lek\",\"" +
                    "AMD\":\"Armenian Dram\",\"ANG\":\"Netherlands Antillean Guilder\",\"AOA\":\"Angolan " +
                    "Kwanza\",\"ARS\":\"Argentine Peso\",\"AUD\":\"Australian Dollar\",\"AWG\":\"Aruban " +
                    "Florin\",\"AZN\":\"Azerbaijani Manat\",\"BAM\":\"Bosnia-Herzegovina Convertible Mark\",\"" +
                    "BBD\":\"Barbadian Dollar\",\"BDT\":\"Bangladeshi Taka\",\"BGN\":\"Bulgarian Lev\",\"" +
                    "BHD\":\"Bahraini Dinar\",\"BIF\":\"Burundian Franc\",\"BMD\":\"Bermudan Dollar\",\"" +
                    "BND\":\"Brunei Dollar\",\"BOB\":\"Bolivian Boliviano\",\"BRL\":\"Brazilian Real\",\"" +
                    "BSD\":\"Bahamian Dollar\",\"BTC\":\"Bitcoin\",\"BTN\":\"Bhutanese Ngultrum\",\"BWP\":\"" +
                    "Botswanan Pula\",\"BYN\":\"New Belarusian Ruble\",\"BYR\":\"Belarusian R" +
                    "uble\",\"BZD\":\"Belize Dollar\",\"CAD\":\"Canadian Dollar\",\"CDF\":\"Co" +
                    "ngolese Franc\",\"CHF\":\"Swiss Franc\",\"CLF\":\"Chilean Unit of Accoun" +
                    "t (UF)\",\"CLP\":\"Chilean Peso\",\"CNY\":\"Chinese Yuan\",\"COP\":\"Col" +
                    "ombian Peso\",\"CRC\":\"Costa Rican Col\\u00f3n\",\"CUC\":\"Cuban Conver" +
                    "tible Peso\",\"CUP\":\"Cuban Peso\",\"CVE\":\"Cape Verdean Escudo\",\"CZK\":\"Cz" +
                    "ech Republic Koruna\",\"DJF\":\"Djiboutian Franc\",\"DKK\":\"Danish Krone\",\"DO" +
                    "P\":\"Dominican Peso\",\"DZD\":\"Algerian Dinar\",\"EGP\":\"Egyptian Pound\",\"E" +
                    "RN\":\"Eritrean Nakfa\",\"ETB\":\"Ethiopian Birr\",\"EUR\":\"Euro\",\"FJD\":\"F" +
                    "ijian Dollar\",\"FKP\":\"Falkland Islands Pound\",\"GBP\":\"British Poun" +
                    "d Sterling\",\"GEL\":\"Georgian Lari\",\"GGP\":\"Guernsey Pound\",\"GH" +
                    "S\":\"Ghanaian Cedi\",\"GIP\":\"Gibraltar Pound\",\"GMD\":\"Gambian Dal" +
                    "asi\",\"GNF\":\"Guinean Franc\",\"GTQ\":\"Guatemalan Quetzal\",\"GYD\":\"G" +
                    "uyanaese Dollar\",\"HKD\":\"Hong Kong Dollar\",\"HNL\":\"Honduran Lempi" +
                    "ra\",\"HRK\":\"Croatian Kuna\",\"HTG\":\"Haitian Gourde\",\"HUF\":\"Hunga" +
                    "rian Forint\",\"IDR\":\"Indonesian Rupiah\",\"ILS\":\"Israeli New Sheq" +
                    "el\",\"IMP\":\"Manx pound\",\"INR\":\"Indian Rupee\",\"IQD\":\"Iraqi Dina" +
                    "r\",\"IRR\":\"Iranian Rial\",\"ISK\":\"Icelandic Kr\\u00f3na\",\"JEP\":\"Je" +
                    "rsey Pound\",\"JMD\":\"Jamaican Dollar\",\"JOD\":\"Jordanian Dinar\",\"JPY\":\"Ja" +
                    "panese Yen\",\"KES\":\"Kenyan Shilling\",\"KGS\":\"Kyrgystani Som\",\"KHR\":\"Cam" +
                    "bodian Riel\",\"KMF\":\"Comorian Franc\",\"KPW\":\"North Korean Won\",\"KRW\":\"So" +
                    "uth Korean Won\",\"KWD\":\"Kuwaiti Dinar\",\"KYD\":\"Cayman Islands Dollar\",\"KZT\":\"Kazakhstani Tenge\",\"LAK\":\"Laotian Kip\",\"LBP\":\"Lebanese Pound\",\"LKR\":\"Sri Lankan Rupee\",\"LRD\":\"Liberian Dollar\",\"LSL\":\"Lesotho Loti\",\"LTL\":\"Lithuanian Litas\",\"LVL\":\"Latvian Lats\",\"LYD\":\"Libyan Dinar\",\"MAD\":\"Moroccan Dirham\",\"MDL\":\"Moldovan Leu\",\"MGA\":\"Malagasy Ariary\",\"MKD\":\"Macedonian Denar\",\"MMK\":\"Myanma Kyat\",\"MNT\":\"Mongolian Tugrik\",\"MOP\":\"Macanese Pataca\",\"MRO\":\"Mauritanian Ouguiya\",\"MUR\":\"Mauritian Rupee\",\"MVR\":\"Maldivian Rufiyaa\",\"MWK\":\"Malawian Kwacha\",\"MXN\":\"Mexican Peso\",\"MYR\":\"Malaysian Ringgit\",\"MZN\":\"Mozambican Metical\",\"NAD\":\"Namibian Dollar\",\"NGN\":\"Nigerian Naira\",\"NIO\":\"Nicaraguan C\\u00f3rdoba\",\"NOK\":\"Norwegian Krone\",\"NPR\":\"Nepalese Rupee\",\"NZD\":\"New Zealand Dollar\",\"OMR\":\"Omani Rial\",\"PAB\":\"Panamanian Balboa\",\"PEN\":\"Peruvian Nuevo Sol\",\"PGK\":\"Papua New Guinean Kina\",\"PHP\":\"Philippine Peso\",\"PKR\":\"Pakistani Rupee\",\"PLN\":\"Polish Zloty\",\"PYG\":\"Paraguayan Guarani\",\"QAR\":\"Qatari Rial\",\"RON\":\"Romanian Leu\",\"RSD\":\"Serbian Dinar\",\"RUB\":\"Russian Ruble\",\"RWF\":\"Rwandan Franc\",\"SAR\":\"Saudi Riyal\",\"SBD\":\"Solomon Islands Dollar\",\"SCR\":\"Seychellois Rupee\",\"SDG\":\"Sudanese Pound\",\"SEK\":\"Swedish Krona\",\"SGD\":\"Singapore Dollar\",\"SHP\":\"Saint Helena Pound\",\"SLL\":\"Sierra Leonean Leone\",\"SOS\":\"Somali Shilling\",\"SRD\":\"Surinamese Dollar\",\"STD\":\"S\\u00e3o Tom\\u00e9 and Pr\\u00edncipe Dobra\",\"SVC\":\"Salvadoran Col\\u00f3n\",\"SYP\":\"Syrian Pound\",\"SZL\":\"Swazi Lilangeni\",\"THB\":\"Thai Baht\",\"TJS\":\"Tajikistani Somoni\",\"TMT\":\"Turkmenistani Manat\",\"TND\":\"Tunisian Dinar\",\"TOP\":\"Tongan Pa\\u02bbanga\",\"TRY\":\"Turkish Lira\",\"TTD\":\"Trinidad and Tobago Dollar\",\"TWD\":\"New Taiwan Dollar\",\"TZS\":\"Tanzanian Shilling\",\"UAH\":\"Ukrainian Hryvnia\",\"UGX\":\"Ugandan Shilling\",\"USD\":\"United States Dollar\",\"UYU\":\"Uruguayan Peso\",\"UZS\":\"Uzbekistan Som\",\"VEF\":\"Venezuelan Bol\\u00edvar Fuerte\",\"VND\":\"Vietnamese Dong\",\"VUV\":\"Vanuatu Vatu\",\"WST\":\"Samoan Tala\",\"XAF\":\"CFA Franc BEAC\",\"XAG\":\"Silver (troy ounce)\",\"XAU\":\"Gold (troy ounce)\",\"XCD\":\"East Caribbean Dollar\",\"XDR\":\"Special Drawing Rights\",\"XOF\":\"CFA Franc BCEAO\",\"XPF\":\"CFP Franc\",\"YER\":\"Yemeni Rial\",\"ZAR\":\"South African Rand\",\"ZMK\":\"Zambian Kwacha (pre-2013)\",\"ZMW\":\"Zambian Kwacha\",\"ZWL\":\"Zimbabwean Dollar\"}}",
            SupportedCurrenciesResponse::class.java
        )
    }
}
