package victoriaslmn.android.viper.sample.presentation.messages.bycontact;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import victoriaslmn.android.viper.sample.R;
import victoriaslmn.android.viper.sample.domain.contacts.Contact;
import victoriaslmn.android.viper.sample.presentation.common.Layout;
import victoriaslmn.android.viper.sample.presentation.messages.MessagesActivity;
import victoriaslmn.android.viper.sample.presentation.messages.common.BaseMessagesFragment;

@Layout(id = R.layout.recycler_view)
public class ByContactMessagesFragment extends BaseMessagesFragment<ByContactMessagesPresenter> implements ByContactMessagesView {
    private static final String CONTACT = "CONTACT";

    @Bind(R.id.chats_recycler_view)
    RecyclerView recyclerView;

    private MessagesAdapter messagesAdapter;
    private String title = null;

    public static ByContactMessagesFragment newInstance(Contact contact) {
        ByContactMessagesFragment fragment = new ByContactMessagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Contact contact = (Contact) getArguments().getSerializable(CONTACT);
        assert contact != null;
        getPresenter().init(contact);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getFabButtonIcon() {
        return 0;
    }

    @Override
    public View.OnClickListener getFabButtonAction() {
        return null;
    }

    @Override
    public void resolveTitle(String title) {
        this.title = title;
        ((MessagesActivity) getActivity()).resolveToolbar(this);
    }

    @Override
    public void setMessages(List<MessageViewModel> viewModels) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messagesAdapter = new MessagesAdapter(viewModels);
        recyclerView.setAdapter(messagesAdapter);
    }

    @Override
    public void updateMessages() {
        messagesAdapter.notifyDataSetChanged();
    }
}