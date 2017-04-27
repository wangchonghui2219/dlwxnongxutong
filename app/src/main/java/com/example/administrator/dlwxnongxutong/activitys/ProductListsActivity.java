package com.example.administrator.dlwxnongxutong.activitys;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.person.Product;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListsActivity extends BaseActiVity {

    private Toolbar tb_toolbar;
    private List<Product> products = new ArrayList<>();
    private LRecyclerView rlv_product_list;

    @Override
    public void initView() {
        setContentView(R.layout.activity_product_lists);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlv_product_list = (LRecyclerView) findViewById(R.id.rlv_product_list);

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            Product product = new Product();
            product.setProductName("农畜通" + i);
            product.setProductIntegral(i + "积分");
            product.setProductPrice("￥" + i + "00");
            product.setPhotoUrl(R.mipmap.icon_home_product);
            products.add(product);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(ctx,2);
        rlv_product_list.setLayoutManager(layoutManager);

//        ProductAdapter productAdapter = new ProductAdapter(products,ctx);
//        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(productAdapter);
//        rlv_product_list.setAdapter(lRecyclerViewAdapter);
//        rlv_product_list.setHasFixedSize(true);
    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setSearchView(menu);
        return true;
    }

    /**
     * 设置搜索栏功能
     * @param menu
     */
    private void setSearchView(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.ab_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ProductListsActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
