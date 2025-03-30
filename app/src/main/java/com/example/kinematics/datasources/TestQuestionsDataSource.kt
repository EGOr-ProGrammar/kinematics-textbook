package com.example.kinematics.datasources

import com.example.kinematics.models.TestQuestion

object TestQuestionsDataSource {
    val questions1 = listOf<TestQuestion>(
        TestQuestion(
            questionText = "Истинность теории базируется на\n" +
                    "А) достоверности экспериментов, лежащих в её основе\n" +
                    "Б) экспериментальном подтверждении выводов из неё?",
            options = listOf(
                "1) только А", "2) только Б", "3) и А, и Б", "4) ни А, ни Б"
            ),
            correctAnswerIndex = 2,
        ),
        TestQuestion(
            questionText = "Исследуется перемещение слона и мухи. Модель материальной точки может " +
                    "использоваться для описания движения\n",
            options = listOf(
                "1) только слона", "2) только мухи", "3) и слона, и мухи в разных исследованиях",
                "4) ни слона, ни мухи, поскольку это живые существа"
            ),
            correctAnswerIndex = 1
        ),
        TestQuestion(
            questionText = " Решаются две задачи:\n" +
                    "А. Рассчитывается манёвр стыковки двух космических кораблей.\n" +
                    "Б. Рассчитываются периоды обращения космических кораблей вокруг Земли.\n" +
                    "В каком случае космические корабли можно рассматривать как материальные точки?",
            options = listOf(
                "1) только в первом", "2) только во втором",
                "3) в обоих случаях", "4) ни в первом, ни во втором"
            ),
            correctAnswerIndex = 1
        ),
        TestQuestion(
            questionText = " Когда мы говорим, что смена дня и ночи на Земле объясняется восходом " +
                    "и заходом Солнца, то мы имеем в виду систему отсчёта, связанную с",
            options = listOf(
                "1) Солнцем", "2) Землёй", "3) планетами", "4) любым телом"
            ),
            correctAnswerIndex = 1
        ),
        TestQuestion(
            questionText = "Чтобы было проще рассчитать время движения автобуса между двумя " +
                    "остановками, надо в качестве тела отсчёта выбрать",
            options = listOf(
                "1) автобус", "2) проезжающую мимо машину",
                "3) шоссе, по которому он движется", "4) идущего по тротуару пешехода"
            ),
            correctAnswerIndex = 2
        )
    )
}