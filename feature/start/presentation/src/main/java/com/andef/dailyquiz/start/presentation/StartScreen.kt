package com.andef.dailyquiz.start.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andef.dailyquiz.core.design.DailyQuizTheme
import com.andef.dailyquiz.core.design.button.type.UiButtonType
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.navigation.routes.Screen

@Composable
fun StartScreen(navHostController: NavHostController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UiButton(
            modifier = Modifier.padding(top = 52.dp),
            text = stringResource(R.string.history),
            type = UiButtonType.WithIcon(
                icon = painterResource(com.andef.dailyquiz.core.design.R.drawable.history),
                contentDescription = stringResource(com.andef.dailyquiz.core.design.R.string.history_icon)
            ),
            onClick = { navHostController.navigate(Screen.History.route) }
        )
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 116.dp)
                .padding(horizontal = 40.dp),
            painter = painterResource(com.andef.dailyquiz.core.design.R.drawable.logo),
            contentDescription = stringResource(R.string.daily_quiz_logo)
        )
        WelcomeCardWithStartButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp)
                .padding(horizontal = 20.dp),
            onStartClick = { navHostController.navigate(Screen.Quiz.route) }
        )
    }
}

@Composable
private fun WelcomeCardWithStartButton(modifier: Modifier, onStartClick: () -> Unit) {
    UiCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.welcome_to_daily_quiz),
                fontSize = 28.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center
            )
            UiButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.start_quiz),
                onClick = onStartClick
            )
        }
    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    DailyQuizTheme {
        Scaffold {
            StartScreen(navHostController = rememberNavController(), paddingValues = it)
        }
    }
}