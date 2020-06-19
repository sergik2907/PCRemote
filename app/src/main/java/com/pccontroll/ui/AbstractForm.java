package com.pccontroll.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.pccontroll.BR;
import com.pccontroll.R;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.jetbrains.annotations.NotNull;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/05/31
 */
public abstract class AbstractForm<B extends ViewDataBinding, V extends AbstractViewModel> extends Fragment {

	private B binding;
	private V viewModel;
	private int layoutId;
	private NavController navController;
	public AbstractForm(int layoutId) {
		super();
		this.layoutId = layoutId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
		return binding.getRoot();
	}

	@Override
	public void onStart() {
		viewModel.onStart();
		super.onStart();
	}

	@Override
	public void onDestroy() {
		viewModel.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		navController = Navigation.findNavController(getActivity(), R.id.nav_host_form);
		viewModel = new ViewModelProvider(navController.getViewModelStoreOwner(R.id.nav_graph), new ViewModelProvider.Factory() {
			@NotNull
			@Override
			public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
				return (T) AbstractViewModel.getInstance(AbstractForm.this, getVMClassValue());
			}
		})
				.get(this.getClass().getSimpleName() + hashCode(), getVMClassValue());
		binding.setVariable(BR.vm, viewModel);
		binding.setLifecycleOwner(this);
	}

	private Class<V> getVMClassValue() {
		Class aClass = getClass();
		ParameterizedType parameterizedType = (ParameterizedType) aClass.getGenericSuperclass();
		Type type = parameterizedType.getActualTypeArguments()[1];
		return (Class<V>) type;
	}

	public V getViewModel() {
		return viewModel;
	}

	public NavController getNavController() {
		return navController;
	}
}