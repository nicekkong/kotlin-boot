package com.nicekkong.kotlinboot.Utils

import humanize.Humanize

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


object DateUtils  {

    fun toStringLocalDateTimeWithFormat(time: LocalDateTime?, format: String?): String? {
        val formatter = DateTimeFormatter.ofPattern(format)
        return time?.let { temporal: LocalDateTime? -> formatter.format(temporal) }
                    ?:""
    }

    fun toStringYYYYMMDDHHMMSS(time: LocalDateTime?): String? {
        return toStringLocalDateTimeWithFormat(time, "yyyyMMddHHmmss")
    }

    /**
     * 경과 기간에 따른 일시 표시
     * ~ 1H : 방금 전
     * ~ 24H : OO시간 전
     * ~ 1Days : YYYY.MM.DD
     * @param time
     * @return
     */
    fun toHumanizeDateTime(time: LocalDateTime): String? {
        return if (Period.between(time.toLocalDate(), LocalDateTime.now().toLocalDate()).days >= 1) {
            time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        } else if (Duration.between(
                LocalTime.of(time.hour, time.minute),
                LocalTime.of(LocalTime.now().hour, LocalTime.now().minute)
            ).toHours() >= 1
        ) {
            Humanize.naturalTime(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
        } else {
            "방금 전"
        }
    }
}