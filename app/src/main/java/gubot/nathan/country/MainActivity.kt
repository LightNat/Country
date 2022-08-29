package gubot.nathan.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    //Variables for The Components
    lateinit var selectedCountry: TextView
    lateinit var startButton: Button
    lateinit var restartButton: Button
    lateinit var sumbitButton: Button
    lateinit var answerText: EditText
    lateinit var countryImage: ImageView
    lateinit var myScore: TextView
    lateinit var mistake_1: TextView
    lateinit var mistake_2: TextView
    lateinit var mistake_3: TextView


    //Variables
    var countryID: Int = 0
    var score = 0
    var mistake = 0
    lateinit var randomCountry: MutableMap.MutableEntry<String, String>


    var country = mutableMapOf(
        "Afghanistan" to "Kabul", "Albania" to "Tirana", "Algeria" to "Algiers", "Andorra" to "Andorra la Vella", "Angola" to "Luanda",
        "Antigua and Barbuda" to "Saint John's", "Argentina" to "Buenos Aires", "Armenia" to "Yerevan", "Australia" to "Canberra", "Austria" to "Vienna",
        "Azerbaijan" to "Baku", "Bahamas" to "Nassau", "Bahrain" to "Manama", "Bangladesh" to "Daka", "Barbados" to "Bridgetown",
        "Belarus" to "Minsk", "Belgium" to "Brussels", "Belize" to "Belmopan", "Benin" to "Porto-Novo", "Bhutan" to "Thimphu",
        "Bolivia" to "Sucre", "Bosnia and Herzegovina" to "Sarajevo", "Botswana" to "Gaborone", "Brazil" to "Brasilia", "Brunei" to "Bandar Seri Begawan",
        "Bulgaria" to "Sofia", "Burkina Faso" to "Ouagadougou", "Burundi" to "Gitega", "Côte d'Ivoire" to "Yamoussoukro", "Cabo Verde" to "Praia",
        "Cambodia" to "Phnom Penh", "Cameroon" to "Yaoundé", "Canada" to "Ottawa", "Central African Republic" to "Bangui", "Chad" to "N'Djamena",
        "Chile" to "Santiago", "China" to "Beijing", "Colombia" to "Bogotá", "Comoros" to "Moroni", "Congo (Congo-Brazzaville)" to "Brazzaville",
        "Costa Rica" to "San José", "Croatia" to "Zagreb", "Cuba" to "Havana", "Cyprus" to "Nicosia", "Czechia (Czech Republic)" to "Prague",
        "Democratic Republic of the Congo" to "Kinshasa", "Denmark" to "Copenhagen", "Djibouti" to "Djibouti City", "Dominica" to "Roseau", "Dominican Republic" to "Santo Domingo",
        "Ecuador" to "Quito", "Egypt" to "Cairo", "El Salvador" to "San Salvador", "Equatorial Guinea" to "Malabo", "Eritrea" to "Asmara",
        "Estonia" to "Tallinn", "Eswatini fmr. Swaziland" to "Mbabane", "Ethiopia" to "Addis Ababa", "Fiji" to "Suva", "Finland" to "Helsinki",
        "France" to "Paris", "Gabon" to "Libreville", "Gambia" to "Banjul", "Georgia" to "Tbilisi", "Germany" to "Berlin",
        "Ghana" to "Accra", "Greece" to "Athens", "Grenada" to "Saint George's", "Guatemala" to "Guatemala City", "Guinea" to "Conakry",
        "Guinea-Bissau" to "Bissau", "Guyana" to "Georgetown", "Haiti" to "Port-au-Prince", "Holy See" to "Vatican City", "Honduras" to "Tegucigalpa",
        "Hungary" to "Budapest", "Iceland" to "Reykjavík", "India" to "New Delhi", "Indonesia" to "Jakarta", "Iran" to "Tehran",
        "Iraq" to "Baghdad", "Ireland" to "Dublin", "Israel" to "Jerusalem", "Italy" to "Rome", "Jamaica" to "Kingston",
        "Japan" to "Tokyo", "Jordan" to "Amman", "Kazakhstan" to "Nur-Sultan", "Kenya" to "Nairobi", "Kiribati" to "Tarawa",
        "Kuwait" to "Kuwait City", "Kyrgyzstan" to "Bishkek", "Laos" to "Vientiane", "Latvia" to "Riga", "Lebanon" to "Beirut",
        "Lesotho" to "Maseru", "Liberia" to "Monrovia", "Libya" to "Tripoli", "Liechtenstein" to "Vaduz", "Lithuania" to "Vilnius",
        "Luxembourg" to "Luxembourg City", "Madagascar" to "Antananarivo", "Malawi" to "Lilongwe", "Malaysia" to "Federal Territory of Kuala Lumpur", "Maldives" to "Malé",
        "Mali" to "Bamako", "Malta" to "Valletta", "Marshall Islands" to "Majuro", "Mauritania" to "Nouakchott", "Mauritius" to "Port Louis",
        "Mexico" to "Mexico City", "Micronesia" to "Palikir", "Moldova" to "Chișinău", "Mongolia" to "Ulaanbaatar", "Montenegro" to "Podgorica",
        "Morocco" to "Rabat", "Mozambique" to "Maputo", "Myanmar (formerly Burma)" to "Rangoon", "Namibia" to "Windhoek", "Nauru" to "Yaren",
        "Nepal" to "Kathmandu", "Netherlands" to "Amsterdam", "New Zealand" to "Wellington", "Nicaragua" to "Managua", "Niger" to "Niamey",
        "Nigeria" to "Abuja", "North Korea" to "Pyongyang", "North Macedonia" to "Skopje", "Norway" to "Oslo", "Oman" to "Muscat",
        "Pakistan" to "Islamabad", "Palau" to "Ngerulmud", "Palestine State" to "Jerusalem", "Panama" to "Panama City", "Papua New Guinea" to "Port Moresby",
        "Paraguay" to "Asunción", "Peru" to "Lima", "Philippines" to "Manila", "Poland" to "Warsaw", "Portugal" to "Lisbon",
        "Qatar" to "Doha", "Romania" to "Bucharest", "Russia" to "Moscow", "Rwanda" to "Kigali", "Saint Kitts and Nevis" to "Basseterre",
        "Saint Lucia" to "Castries", "Saint Vincent and the Grenadines" to "Kingstown", "Samoa" to "Apia", "San Marino" to "San Marino City", "Sao Tome and Principe" to "Sao Tome City",
        "Saudi Arabia" to "Riyadh", "Senegal" to "Dakar", "Serbia" to "Belgrade", "Seychelles" to "Victoria", "Sierra Leone" to "Freetown",
        "Singapore" to "Singapore", "Slovakia" to "Bratislava", "Slovenia" to "Ljubljana", "Solomon Islands" to "Honiara", "Somalia" to "Mogadishu",
        "South Africa" to "Pretori", "South Korea" to "Seoul", "South Sudan" to "Juba", "Spain" to "Madrid", "Sri Lanka" to "Sri Jayawardenepura Kotte",
        "Sudan" to "Khartoum", "Suriname" to "Paramaribo", "Sweden" to "Stockholm", "Switzerland" to "Bern", "Syria" to "Damascus",
        "Tajikistan" to "Dushanbe", "Tanzania" to "Dodoma", "Thailand" to "Bangkok", "Timor-Leste" to "Dili", "Togo" to "Lomé",
        "Tonga" to "Nuku'alofa", "Trinidad and Tobago" to "Port of Spain", "Tunisia" to "Tunis", "Turkey" to "Ankara", "Turkmenistan" to "Ashgabat",
        "Tuvalu" to "Funafuti", "Uganda" to "Kampala", "Ukraine" to "Kyiv", "United Arab Emirates" to "Abu Dhabi", "United Kingdom" to "London",
        "United States of America" to "Washington, D.C.", "Uruguay" to "Montevideo", "Uzbekistan" to "Tashkent", "Vanuatu" to "Port Vila", "Venezuela" to "Caracas",
        "Vietnam" to "Hanoi", "Yemen" to "Sana'a", "Zambia" to "Lusaka", "Zimbabwe" to "Harare"
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mistake_1 = findViewById(R.id.mistake1)
        mistake_2 = findViewById(R.id.mistake2)
        mistake_3 = findViewById(R.id.mistake3)
        mistake_1.visibility = View.INVISIBLE
        mistake_2.visibility = View.INVISIBLE
        mistake_3.visibility = View.INVISIBLE

        restartButton = findViewById(R.id.restart_button)
        restartButton.visibility = View.INVISIBLE

        country()
        restart()

        countryImage = findViewById(R.id.country_image)
        sumbitButton = findViewById(R.id.submit_button)

    }

    private fun country() {


        selectedCountry = findViewById(R.id.country_selected)
        startButton = findViewById(R.id.start_button)
        answerText = findViewById(R.id.answer)
        myScore = findViewById(R.id.score)


        startButton.setOnClickListener {

            startButton.isEnabled = false

            randomCOuntry()

            submit()

        }
    }

    private fun submit()
    {

        sumbitButton.setOnClickListener {

            //Toast.makeText(this, randomCountry.value, Toast.LENGTH_SHORT).show()
            if (answerText.text.toString().equals(randomCountry.value))
            {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                randomCOuntry()
                score += 1
                myScore.text = score.toString()
                answerText.text.clear()
            }
            else
            {
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show()
                randomCOuntry()
                answerText.text.clear()

                mistake += 1
                if(mistake == 1)
                {
                    mistake_1.visibility = View.VISIBLE
                }
                else if(mistake == 2)
                {
                    mistake_2.visibility = View.VISIBLE
                }
                else if(mistake == 3)
                {
                    mistake_3.visibility = View.VISIBLE

                    sumbitButton.isEnabled = false

                    restartButton.visibility = View.VISIBLE
                    Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun randomCOuntry()
    {
        randomCountry = country.entries.shuffled().elementAt(Random.nextInt(country.size))

        //Toast.makeText(this, randomCountry.key, Toast.LENGTH_SHORT).show()
        selectedCountry.text = randomCountry.key

        countryID = when(randomCountry.key)
        {
            "Afghanistan" ->  1
            "Albania" -> 2
            "Algeria" -> 3
            "Andorra" -> 4
            "Angola" -> 5
            "Antigua and Barbuda" -> 6
            "Argentina" -> 7
            "Armenia" -> 8
            "Australia" -> 9
            "Austria" -> 10
            "Azerbaijan" -> 11
            "Bahamas" -> 12
            "Bahrain" -> 13
            "Bangladesh" -> 14
            "Barbados" -> 15
            "Belarus" -> 16
            "Belgium" -> 17
            "Belize" -> 18
            "Benin" -> 19
            "Bhutan" -> 20
            "Bolivia" -> 21
            "Bosnia and Herzegovina" -> 22
            "Botswana" -> 23
            "Brazil" -> 24
            "Brunei" -> 25
            "Bulgaria" -> 26
            "Burkina Faso" -> 27
            "Burundi" -> 28
            "Côte d'Ivoire" -> 29
            "Cabo Verde" -> 30
            "Cambodia" -> 31
            "Cameroon" -> 32
            "Canada" -> 33
            "Central African Republic" -> 34
            "Chad" -> 35
            "Chile" -> 36
            "China" -> 37
            "Colombia" -> 38
            "Comoros" -> 39
            "Congo (Congo-Brazzaville)" -> 40
            "Costa Rica" -> 41
            "Croatia" -> 42
            "Cuba" -> 43
            "Cyprus" -> 44
            "Czechia (Czech Republic)" -> 45
            "Democratic Republic of the Congo" -> 46
            "Denmark" -> 47
            "Djibouti" -> 48
            "Dominica" -> 49
            "Dominican Republic" -> 50
            "Ecuador" -> 51
            "Egypt" -> 52
            "El Salvador" -> 53
            "Equatorial Guinea" -> 54
            "Eritrea" -> 55
            "Estonia" -> 56
            "Eswatini fmr. Swaziland" -> 57
            "Ethiopia" -> 58
            "Fiji" -> 59
            "Finland" -> 60
            "France" -> 61
            "Gabon" -> 62
            "Gambia" -> 63
            "Georgia" -> 64
            "Germany" -> 65
            "Ghana" -> 66
            "Greece" -> 67
            "Grenada" -> 68
            "Guatemala" -> 69
            "Guinea" -> 70
            "Guinea-Bissau" -> 71
            "Guyana" -> 72
            "Haiti" -> 73
            "Holy See" -> 74
            "Honduras" -> 75
            "Hungary" -> 76
            "Iceland" -> 77
            "India" -> 78
            "Indonesia" -> 79
            "Iran" -> 80
            "Iraq" -> 81
            "Ireland" -> 82
            "Israel" -> 83
            "Italy" -> 84
            "Jamaica" -> 85
            "Japan" -> 86
            "Jordan" -> 87
            "Kazakhstan" -> 88
            "Kenya" -> 89
            "Kiribati" -> 90
            "Kuwait" -> 91
            "Kyrgyzstan" -> 92
            "Laos" -> 93
            "Latvia" -> 94
            "Lebanon" -> 95
            "Lesotho" -> 96
            "Liberia" -> 97
            "Libya" -> 98
            "Liechtenstein" -> 99
            "Lithuania" -> 100
            "Luxembourg" -> 101
            "Madagascar" -> 102
            "Malawi" -> 103
            "Malaysia" -> 104
            "Maldives" -> 105
            "Mali" -> 106
            "Malta" -> 107
            "Marshall Islands" -> 108
            "Mauritania" -> 109
            "Mauritius" -> 110
            "Mexico" -> 111
            "Micronesia" -> 112
            "Moldova" -> 113
            "Mongolia" -> 114
            "Montenegro" -> 115
            "Morocco" -> 116
            "Mozambique" -> 117
            "Myanmar (formerly Burma)" -> 118
            "Namibia" -> 119
            "Nauru" -> 120
            "Nepal" -> 121
            "Netherlands" -> 122
            "New Zealand" -> 123
            "Nicaragua" -> 124
            "Niger" -> 125
            "Nigeria" -> 126
            "North Korea" -> 127
            "North Macedonia" -> 128
            "Norway" -> 129
            "Oman" -> 130
            "Pakistan" -> 131
            "Palau" -> 132
            "Palestine State" -> 133
            "Panama" -> 134
            "Papua New Guinea" -> 135
            "Paraguay" -> 136
            "Peru" -> 137
            "Philippines" -> 138
            "Poland" -> 139
            "Portugal" -> 140
            "Qatar" -> 141
            "Romania" -> 142
            "Russia" -> 143
            "Rwanda" -> 144
            "Saint Kitts and Nevis" -> 145
            "Saint Lucia" -> 146
            "Saint Vincent and the Grenadines" -> 147
            "Samoa" -> 148
            "San Marino" -> 149
            "Sao Tome and Principe" -> 150
            "Saudi Arabia" -> 151
            "Senegal" -> 152
            "Serbia" -> 153
            "Seychelles" -> 154
            "Sierra Leone" -> 155
            "Singapore" -> 156
            "Slovakia" -> 157
            "Slovenia" -> 158
            "Solomon Islands" -> 159
            "Somalia" -> 160
            "South Africa" -> 161
            "South Korea" -> 162
            "South Sudan" -> 163
            "Spain" -> 164
            "Sri Lanka" -> 165
            "Sudan" -> 166
            "Suriname" -> 167
            "Sweden" -> 168
            "Switzerland" -> 169
            "Syria" -> 170
            "Tajikistan" -> 171
            "Tanzania" -> 172
            "Thailand" -> 173
            "Timor-Leste" -> 174
            "Togo" -> 175
            "Tonga" -> 176
            "Trinidad and Tobago" -> 177
            "Tunisia" -> 178
            "Turkey" -> 179
            "Turkmenistan" -> 180
            "Tuvalu" -> 181
            "Uganda" -> 182
            "Ukraine" -> 183
            "United Arab Emirates" -> 184
            "United Kingdom" -> 185
            "United States of America" -> 186
            "Uruguay" -> 187
            "Uzbekistan" -> 188
            "Vanuatu" -> 189
            "Venezuela" -> 190
            "Vietnam" -> 191
            "Yemen" -> 192
            "Zambia" -> 193
            "Zimbabwe" -> 194
            else -> 1000
        }

        val drawableResource = when(countryID)
        {
            1 -> R.drawable.afghanistan
            2 -> R.drawable.albania
            3 -> R.drawable.algeria
            4 -> R.drawable.andorra
            5 -> R.drawable.angola
            6 -> R.drawable.antigua_and_barbuda
            7 -> R.drawable.argentina
            8 -> R.drawable.armenia
            9 -> R.drawable.australia
            10 -> R.drawable.austria
            11 -> R.drawable.azerbaijan
            12 -> R.drawable.bahamas
            13 -> R.drawable.bahrain
            14 -> R.drawable.bangladesh
            15 -> R.drawable.barbados
            16 -> R.drawable.belarus
            17 -> R.drawable.belgium
            18 -> R.drawable.belize
            19 -> R.drawable.benin
            20 -> R.drawable.bhutan
            21 -> R.drawable.bolivia
            22 -> R.drawable.bosnia_and_herzegovina
            23 -> R.drawable.botswana
            24 -> R.drawable.brazil
            25 -> R.drawable.brunei
            26 -> R.drawable.bulgaria
            27 -> R.drawable.burkina_faso
            28 -> R.drawable.burundi
            29 -> R.drawable.cote_dlvoire
            30 -> R.drawable.cabo_verde
            31 -> R.drawable.cambodia
            32 -> R.drawable.cameroon
            33 -> R.drawable.canada
            34 -> R.drawable.central_african_republic
            35 -> R.drawable.chad
            36 -> R.drawable.chile
            37 -> R.drawable.china
            38 -> R.drawable.colombia
            39 -> R.drawable.comoros
            40 -> R.drawable.congo_congo_brazzaville
            41 -> R.drawable.costa_rica
            42 -> R.drawable.croatia
            43 -> R.drawable.cuba
            44 -> R.drawable.cyprus
            45 -> R.drawable.czechia_czech_republic
            46 -> R.drawable.democratic_republic_of_the_congo
            47 -> R.drawable.denmark
            48 -> R.drawable.djibouti
            49 -> R.drawable.dominica
            50 -> R.drawable.dominican_republic
            51 -> R.drawable.ecuador
            52 -> R.drawable.egypt
            53 -> R.drawable.el_salvador
            54 -> R.drawable.equatorial_guinea
            55 -> R.drawable.eritrea
            56 -> R.drawable.estonia
            57 -> R.drawable.eswatini_fmr_swaziland
            58 -> R.drawable.ethiopia
            59 -> R.drawable.fiji
            60 -> R.drawable.finland
            61 -> R.drawable.france
            62 -> R.drawable.gabon
            63 -> R.drawable.gambia
            64 -> R.drawable.georgia
            65 -> R.drawable.germany
            66 -> R.drawable.ghana
            67 -> R.drawable.greece
            68 -> R.drawable.grenada
            69 -> R.drawable.guatemala
            70 -> R.drawable.guinea
            71 -> R.drawable.guinea_bissau
            72 -> R.drawable.guyana
            73 -> R.drawable.haiti
            74 -> R.drawable.holy_see
            75 -> R.drawable.honduras
            76 -> R.drawable.hungary
            77 -> R.drawable.iceland
            78 -> R.drawable.india
            79 -> R.drawable.indonesia
            80 -> R.drawable.iran
            81 -> R.drawable.iraq
            82 -> R.drawable.ireland
            83 -> R.drawable.israel
            84 -> R.drawable.italy
            85 -> R.drawable.jamaica
            86 -> R.drawable.japan
            87 -> R.drawable.jordan
            88 -> R.drawable.kazakhstan
            89 -> R.drawable.kenya
            90 -> R.drawable.kiribati
            91 -> R.drawable.kuwait
            92 -> R.drawable.kyrgyzstan
            93 -> R.drawable.laos
            94 -> R.drawable.latvia
            95 -> R.drawable.lebanon
            96 -> R.drawable.lesotho
            97 -> R.drawable.liberia
            98 -> R.drawable.libya
            99 -> R.drawable.liechtenstein
            100 -> R.drawable.lithuania
            101 -> R.drawable.luxembourg
            102 -> R.drawable.madagascar
            103 -> R.drawable.malawi
            104 -> R.drawable.malaysia
            105 -> R.drawable.maldives
            106 -> R.drawable.mali
            107 -> R.drawable.malta
            108 -> R.drawable.marshall_islands
            109 -> R.drawable.mauritania
            110 -> R.drawable.mauritius
            111 -> R.drawable.mexico
            112 -> R.drawable.micronesia
            113 -> R.drawable.moldova
            114 -> R.drawable.mongolia
            115 -> R.drawable.montenegro
            116 -> R.drawable.morocco
            117 -> R.drawable.mozambique
            118 -> R.drawable.myanmar_formerly_burma
            119 -> R.drawable.namibia
            120 -> R.drawable.nauru
            121 -> R.drawable.nepal
            122 -> R.drawable.netherlands
            123 -> R.drawable.new_zealand
            124 -> R.drawable.nicaragua
            125 -> R.drawable.niger
            126 -> R.drawable.nigeria
            127 -> R.drawable.north_korea
            128 -> R.drawable.north_macedonia
            129 -> R.drawable.norway
            130 -> R.drawable.oman
            131 -> R.drawable.pakistan
            132 -> R.drawable.palau
            133 -> R.drawable.palestine_state
            134 -> R.drawable.panama
            135 -> R.drawable.papua_new_guinea
            136 -> R.drawable.paraguay
            137 -> R.drawable.peru
            138 -> R.drawable.philippines
            139 -> R.drawable.poland
            140 -> R.drawable.portugal
            141 -> R.drawable.qatar
            142 -> R.drawable.romania
            143 -> R.drawable.russia
            144 -> R.drawable.rwanda
            145 -> R.drawable.saint_kitts_and_nevis
            146 -> R.drawable.saint_lucia
            147 -> R.drawable.saint_vincent_and_the_grenadines
            148 -> R.drawable.samoa
            149 -> R.drawable.san_marino
            150 -> R.drawable.sao_tome_and_principe
            151 -> R.drawable.saudi_arabia
            152 -> R.drawable.senegal
            153 -> R.drawable.serbia
            154 -> R.drawable.seychelles
            155 -> R.drawable.sierra_leone
            156 -> R.drawable.singapore
            157 -> R.drawable.slovakia
            158 -> R.drawable.slovenia
            159 -> R.drawable.solomon_islands
            160 -> R.drawable.somalia
            161 -> R.drawable.south_africa
            162 -> R.drawable.south_korea
            163 -> R.drawable.south_sudan
            164 -> R.drawable.spain
            165 -> R.drawable.sri_lanka
            166 -> R.drawable.sudan
            167 -> R.drawable.suriname
            168 -> R.drawable.sweden
            169 -> R.drawable.switzerland
            170 -> R.drawable.syria
            171 -> R.drawable.tajikistan
            172 -> R.drawable.tanzania
            173 -> R.drawable.thailand
            174 -> R.drawable.timor_leste
            175 -> R.drawable.togo
            176 -> R.drawable.tonga
            177 -> R.drawable.trinidad_and_tobago
            178 -> R.drawable.tunisia
            179 -> R.drawable.turkey
            180 -> R.drawable.turkmenistan
            181 -> R.drawable.tuvalu
            182 -> R.drawable.uganda
            183 -> R.drawable.ukraine
            184 -> R.drawable.united_arab_emirates
            185 -> R.drawable.united_kingdom
            186 -> R.drawable.united_states_of_america
            187 -> R.drawable.uruguay
            188 -> R.drawable.uzbekistan
            189 -> R.drawable.vanuatu
            190 -> R.drawable.venezuela
            191 -> R.drawable.vietnam
            192 -> R.drawable.yemen
            193 -> R.drawable.zambia
            194 -> R.drawable.zimbabwe
            else -> R.drawable.noflag
        }

        countryImage.setImageResource(drawableResource)
    }

    private fun restart()
    {
        restartButton.setOnClickListener {
            restartButton.visibility = View.INVISIBLE

            mistake_1.visibility = View.INVISIBLE
            mistake_2.visibility = View.INVISIBLE
            mistake_3.visibility = View.INVISIBLE

            answerText.text.clear()

            sumbitButton.isEnabled = true

            score = 0
            mistake = 0

            myScore.text  = score.toString()

            randomCOuntry()
        }
    }


}