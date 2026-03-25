package com.example.maplescouter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maplescouter.ui.theme.MapleScouterTheme
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterInfo

@Composable
fun MainScreen(
    state: ApiResult<List<CharacterInfo>>,
    onCharacterClick: (String) -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is ApiResult.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is ApiResult.Success -> {
                    CharacterListScreen(state.data, onCharacterClick)
                }

                is ApiResult.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterListScreen(
    characters: List<CharacterInfo>,
    onCharacterClick: (String) -> Unit
) {
    LazyColumn {
        items(characters) { character ->
            CharacterItem(character, onCharacterClick)
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterInfo,
    onCharacterClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCharacterClick(character.ocid) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "이름: ${character.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "월드: ${character.world}")
            Text(text = "직업: ${character.job}")
            Text(text = "레벨: ${character.level}")
        }
    }
}

@Preview(showBackground = true, name = "성공 상태")
@Composable
fun MainScreenSuccessPreview() {
    MapleScouterTheme {
        MainScreen(
            state = ApiResult.Success(
                listOf(
                    CharacterInfo("1", "캐릭터1", "루나", "히어로", 200),
                    CharacterInfo("2", "캐릭터2", "엘리시움", "팬텀", 210),
                    CharacterInfo("3", "캐릭터3", "크로아", "비숍", 220)
                )
            )
        )
    }
}

@Preview(showBackground = true, name = "로딩 상태")
@Composable
fun MainScreenLoadingPreview() {
    MapleScouterTheme {
        MainScreen(state = ApiResult.Loading)
    }
}

@Preview(showBackground = true, name = "에러 상태")
@Composable
fun MainScreenErrorPreview() {
    MapleScouterTheme {
        MainScreen(state = ApiResult.Error("네트워크 연결을 확인해주세요."))
    }
}
