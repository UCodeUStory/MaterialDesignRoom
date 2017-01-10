package com.example.qiyue.materialdesignadvance.demo2.dbexecutor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;
import com.shizhefei.db.DBExecutor;
import com.shizhefei.db.sql.Sql;
import com.shizhefei.db.sql.SqlFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBExecutorActivity extends AppCompatActivity {

	private DBExecutor dbExecutor;

	private int count = 1;
	private PersonListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbexecutor);


		System.out.println(SqlFactory.find(Person.class));
		System.out.println(SqlFactory.find(Person.class, "age"));
		System.out.println(SqlFactory.find(Person.class, "age", "name"));
		System.out.println(SqlFactory.find(Person.class, "age", "name", "id"));

		System.out.println(SqlFactory.find(Person.class).where("age =? and ( name = ? or name = ? )", new Object[] { 1, "zsy", "uu" }));

		Sql sql = SqlFactory.find(Person.class).where("age", "=", "1").orderBy("age", true);
		System.out.println(sql);

		sql = SqlFactory.update(Person.class, new String[] { "age" }, new Object[] { 1 });
		System.out.println(sql);

		Person person = new Person("zzz", "1", 2,"");
		try {
			sql = SqlFactory.update(person, "age", "name");
			System.out.println(sql);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}


		dbExecutor = DBExecutor.getInstance(this);
		initView();
		/**
		 * 查询所有
		 */

		/**
		 * 按id查询单条
		 */
		Person personOne = dbExecutor.findById(Person.class,2);
		Log.i("qiyue","personOne="+personOne);
		/**
		 * 按条件查询
		 */
		List<Person> personAge = dbExecutor.executeQuery(SqlFactory.find(Person.class).where("age", "=", "5"));
		for (Person person1 : personAge) {
			Log.i("qiyue","personAge="+person1);
		}
	}


	private void initView() {

		RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
		List<Person> persons = new ArrayList<>();
		adapter = new PersonListAdapter(persons);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

		initListener();




		List<Person> findpersons = dbExecutor.findAll(Person.class);
        if (findpersons!=null) {
			count = findpersons.size();
		}
		for (Person person2 : persons) {
			Log.i("qiyue","persons="+person2.toString());
		}
		adapter.setDatas(findpersons);
		adapter.notifyDataSetChanged();

	}

	private void initListener() {
		findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				count++;
				SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = format.format(new Date());

				Person person1 = new Person("qiyue"+count,"2",count,time);
				/**
				 * 插入
				 */
				dbExecutor.insert(person1);
				adapter.getDatas().add(person1);
				adapter.notifyDataSetChanged();
			}
		});

		findViewById(R.id.btn_queryAll).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Person> findpersons = dbExecutor.findAll(Person.class);
				adapter.setDatas(findpersons);
				adapter.notifyDataSetChanged();
			}
		});
		findViewById(R.id.btn_query_name).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Person> personNames = dbExecutor.executeQuery(SqlFactory.find(Person.class).where("name", "=", "qiyue2"));
				adapter.setDatas(personNames);
				adapter.notifyDataSetChanged();
			}
		});
		findViewById(R.id.btn_delete_qiyue5).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isDelete = dbExecutor.execute(SqlFactory.delete(Person.class).where("name", "=", "qiyue5"));
				if (isDelete) {
					List<Person> findpersons = dbExecutor.findAll(Person.class);
					adapter.setDatas(findpersons);
					adapter.notifyDataSetChanged();
				}
			}
		});
		findViewById(R.id.btn_updateName).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isUpdate = dbExecutor.execute(SqlFactory.update(Person.class, new String[] { "name" }, new Object[] { "new" })
						.where("name", "=", "qiyue3"));
				if (isUpdate) {
					List<Person> findpersons = dbExecutor.findAll(Person.class);
					adapter.setDatas(findpersons);
					adapter.notifyDataSetChanged();
				}
			}
		});
	}
}




