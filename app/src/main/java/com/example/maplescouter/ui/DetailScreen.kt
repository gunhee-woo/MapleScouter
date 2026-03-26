package com.example.maplescouter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.maplescouter.ui.theme.MapleScouterTheme
import com.example.maplescouter.viewmodel.DetailViewModel
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    ocid: String?,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val characterBasicState by viewModel.characterBasicState.collectAsState()

    /*
        ocid 값이 바뀔 때마다 fetchCharacterBasic 실행하므로 효율적이고 안전
        리컴포지션이 일어날 때마다 무한 호출되는 문제 해결
     */
    LaunchedEffect(ocid) {
        ocid?.let {
            viewModel.fetchCharacterBasic(it, null)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("캐릭터 상세 정보") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val result = characterBasicState) {
                is ApiResult.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ApiResult.Success -> {
                    DetailContent(characterBasic = result.data)
                }
                is ApiResult.Error -> {
                    Text(
                        text = "에러 발생: ${result.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(characterBasic: CharacterBasic) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "이름: ${characterBasic.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "월드: ${characterBasic.worldName}", modifier = Modifier.padding(top = 8.dp))
        Text(text = "직업: ${characterBasic.className}")
        Text(text = "레벨: ${characterBasic.level}")
        Text(text = "길드: ${characterBasic.guildName ?: "없음"}")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MapleScouterTheme {
        DetailContent(
            characterBasic = CharacterBasic(
                date = null,
                name = "서현동불독",
                worldName = "크로아",
                gender = "남",
                className = "아크메이지(불,독)",
                classLevel = "6",
                level = 288,
                exp = 55952821541089L,
                expRate = "42.244",
                guildName = "StelLive",
                imageUrl = "",
                createDate = "2022-07-08T00:00+09:00",
                accessFlag = true,
                liberationQuestClear = true
            )
        )
    }
}
