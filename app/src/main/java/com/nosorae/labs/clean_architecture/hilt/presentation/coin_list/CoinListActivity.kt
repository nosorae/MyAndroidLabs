package com.nosorae.labs.clean_architecture.hilt.presentation.coin_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.nosorae.labs.clean_architecture.hilt.common.Constants.LOG_TAG_TEST_WORKER
import com.nosorae.labs.clean_architecture.hilt.common.Constants.PARAM_COIN_ID
import com.nosorae.labs.clean_architecture.hilt.domain.model.Coin
import com.nosorae.labs.clean_architecture.hilt.presentation.coin_detail.CoinDetailActivity
import com.nosorae.labs.clean_architecture.hilt.presentation.coin_list.adapter.CoinListAdapter
import com.nosorae.labs.databinding.ActivityCoinListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {

    private val viewModel: CoinListViewModel by viewModels()
    lateinit var binding: ActivityCoinListBinding
    lateinit var rvAdapter: CoinListAdapter

    private val startActivityForResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            activityResult.data?.let { intent ->
                Toast.makeText(this, "${intent.getStringExtra(PARAM_COIN_ID)}", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        observeState()


        viewModel.workInfosByTag.observe(this) { listOfWorkInfo ->
            Log.e(LOG_TAG_TEST_WORKER, "workInfosByTag :\n ${listOfWorkInfo.joinToString("\n") { "$it" }}")
        }

        viewModel.workInfosByChainName.observe(this) { listOfWorkInfo ->
            listOfWorkInfo.forEach { workInfo ->
            }

        }

        binding.btEnqueueWork.setOnClickListener {
            viewModel.timerWorkerChainTest()
        }

    }




    private fun observeState() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is CoinListState.Loading -> {
                    handleLoadingState()
                }
                is CoinListState.Success -> {
                    handleSuccessState(state.coins)
                }
                is CoinListState.Error -> {
                    handleErrorState(state.message)
                }
            }

        }
    }

    private fun handleLoadingState() {
        binding.pbLoading.isGone = false
    }

    private fun handleSuccessState(list: List<Coin>) {
        binding.pbLoading.isGone = true
        rvAdapter.addList(list)
    }

    private fun handleErrorState(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() = with(binding) {
        rvAdapter = CoinListAdapter { coinId ->
            startCoinDetailActivity(coinId)
        }
        rvCoins.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun startCoinDetailActivity(coinId: String) {
        val intent = Intent(this@CoinListActivity, CoinDetailActivity::class.java)
        intent.putExtra(PARAM_COIN_ID, coinId)
        startActivityForResultLauncher.launch(intent)
    }
}