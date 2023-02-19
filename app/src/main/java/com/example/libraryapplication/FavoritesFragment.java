package com.example.libraryapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    //ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();
    //DynamicRVAdapter dynamicRVAdapter;
    //private RecyclerView favRecyclerView;

    private View myView;

    //private HomeFeedActivity homeFeedActivity;

    //private DynamicRVModel dynamicRVModels;
    public FavoritesFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_favorites,container,false);
        /*homeFeedActivity = (HomeFeedActivity) getActivity();

        favRecyclerView = myView.findViewById(R.id.fav_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeFeedActivity);
        favRecyclerView.setLayoutManager(linearLayoutManager);

        dynamicRVAdapter = new DynamicRVAdapter(getContext(), dynamicRVModels, (RVInterface) getContext());
        dynamicRVAdapter.setData(getFavList(), new DynamicRVAdapter.IClickAddFavListener() {
            @Override
            public void onClickAddFav(ImageView addFavImg, DynamicRVModel dynamicRVModel) {

            }
        });
        favRecyclerView.setAdapter(dynamicRVAdapter);*/


        return myView;
    }

    /*
    private ArrayList<DynamicRVModel> getFavList(){
        ArrayList<DynamicRVModel> list = new ArrayList<>();
        //list.add(new DynamicRVModel(R.drawable.));
        list.add(new DynamicRVModel("And Still I Rise", "Maya Angelou", "64", R.drawable.and_still_i_rise_cover, "The book is primarily about self-respect and confidence. In the poem, Angelou reveals how she will overcome anything through her\n" +
                "self-esteem. She shows how nothing can get her down. She will rise to any occasion, and nothing, not even her skin color, will hold her back.."));
        list.add(new DynamicRVModel("Call Us What We Carry", "Amanda Gorman", "240", R.drawable.call_us_cover, "In Call Us What We Carry, Gorman explores history, language, identity, and erasure through an imaginative and intimate\n" +
                "collage. Harnessing the collective grief of a global pandemic, this beautifully designed volume features poems in many inventive styles\n" +
                "and structures and shines a light on a moment of reckoning."));
        list.add(new DynamicRVModel("Catching Fire", "Suzanne Collins", "391", R.drawable.catching_fire_cover, "After arriving safely home from their unprecedented victory in the 74th Annual Hunger Games, Katniss Everdeen\n" +
                "and Peeta Mellark discover that they must do a quick turnaround and begin a Victors Tour. As she and Peeta travel throughout\n" +
                "the districts, Katniss senses a rebellion is stirring. However, President Snow proves that he is still very\n" +
                "much in control when word comes of a cruel change in the rules for the upcoming 75th Hunger Games."));
        list.add(new DynamicRVModel("Forty days of Musa Dagh", "Franz Werfel", "893", R.drawable.forty_days_cover, "The novel tells the true story of several embattled Armenian villages whose inhabitants refused to obey deportation orders\n" +
                "in 1915 and took up arms in self-defense, holding out for, in fact, more than the biblically resonant 40 days of the novel’s title. Many\n" +
                "survived and were rescued by the French Navy."));
        list.add(new DynamicRVModel("Hamlet", "William Shakespeare", "104", R.drawable.hamlet_cover, "The ghost of the King of Denmark tells his son Hamlet to avenge his murder by killing the new king, Hamlets uncle. Hamlet feigns madness,\n" +
                "contemplates life and death, and seeks revenge. His uncle, fearing for his life, also devises plots to kill Hamlet."));
        list.add(new DynamicRVModel("Harry Potter and the Half-Blood Prince", "J.K.Rowling", "672", R.drawable.halfblood_prince_cover, "The war against Voldemort is not going well. Dumbledore is absent from Hogwarts for long stretches of time.\n" +
                "As in all wars, life goes on. Sixth-year students learn to Apparate. The classes are tough, though Harry receives some help from the\n" +
                "mysterious Half-Blood Prince. And all the while Harry searches for the full story of the boy who became Voldemort-hoping to find what\n" +
                "may be his only vulnerability."));
        list.add(new DynamicRVModel("Jane Eyre", "Charlotte Bronte", "536", R.drawable.jane_eyre_cover, "The novel follows the story of Jane, a seemingly plain and simple girl as she battles through lifes struggles. Jane has many\n" +
                "obstacles in her life - her cruel and abusive Aunt Reed, the grim conditions at Lowood school, her love for Rochester and Rochesters marriage\n" +
                "to Bertha."));
        list.add(new DynamicRVModel("Me Before You", "Jojo Moyes", "480", R.drawable.me_before_you, "It is about a woman, Louisa, who is desperate for a job and accepts a position as private caregiver to a young disabled man, a quadriplegic, from a\n" +
                "wealthy family. This man, Will Traynor, had once been on the fast track in life — a high powered business man who pursued a thrilling life of action and adventure."));
        list.add(new DynamicRVModel("Mere Christianity", "C.S.Lewis", "256", R.drawable.mere_christianity, "Lewis aims to prove to the sensible skeptic that God does exist and that He sent His son Jesus Christ to redeem the world. The book begins with\n" +
                "a logical proof for the Christian God and then transitions into a discussion of the common ground upon which all of those of the Christian faith stand together."));
        list.add(new DynamicRVModel("Murder of Roger Ackroyd", "Agatha Christie", "312", R.drawable.murder_of_roger_cover, "Dr. James Sheppard, lives with his older unmarried sister Caroline in the country village of King’s Abbot on the outskirts\n" +
                "of London. As the local physician with an active practice, Dr. Sheppard becomes emmeshed in a mysterious suicide and murder and the ensuing\n" +
                "investigations into them over a nine-day period after the death of Roger Ackroyd. Detective Poirot, despite having decided to retire, takes\n" +
                "the responsibility of finding out who killed Roger Ackroyd."));
        list.add(new DynamicRVModel("Romeo and Juliet", "William Shakespeare", "156", R.drawable.romeo_and_juliet, "It is a tragic love story where the two main characters, Romeo and Juliet, are supposed to be sworn enemies but fall in love. Due to their families ongoing\n" +
                "conflict, they cannot be together, so they kill themselves because they cannot cope with being separated from one another."));
        list.add(new DynamicRVModel("Samvel", "Raffi", "480", R.drawable.samvel_cover, "The novel tells the story of a young nobleman Samvel. His father betrayed his motherland. Now Samvel, as well as all his family, has to make\n" +
                "a choice between his country and father. The novel beautifully describes the abundance of love, loyalty and the reality of war in Armenia in 370s."));
        list.add(new DynamicRVModel("The Alchemist", "Paulo Coelho", "218", R.drawable.alchemist_cover, "The Alchemist is a classic novel in which a boy named Santiago embarks on a journey seeking treasure in the Egyptian pyramids after having a recurring\n" +
                "dream about it and on the way meets mentors, falls in love, and most importantly, learns the true importance of who he is and how to improve himself."));
        list.add(new DynamicRVModel("The Big Sleep", "Raymond Chandler", "277", R.drawable.the_big_sleep_cover, "Private investigator Philip Marlowe is hired by wealthy General Sternwood to stop a blackmailer. Marlowe suspects that the old General\n" +
                "is merely testing his caliber before trusting him with a bigger job, one involving Sternwoods two amoral daughters."));
        list.add(new DynamicRVModel("The Book of Friday", "Hakob Meghapart", "124", R.drawable.urbatagirk_cover, "The Book of Friday, or Urbatagirk in Armenian, was the first printed book in the Armenian language. Its content was partly religious, partly\n" +
                "secular, consisting of cures and prayers for the sick, ancient writings, myths, long quotations from The Book of Sadness (or  The Book of Lamentations) by\n" +
                "Gregory of Narek, the Prayer of Cyprianos of Antioch, the story of the Virgin and Justinian, et cetera."));
        list.add(new DynamicRVModel("The Book of Sadness", "Grigor of Narek", "620", R.drawable.matyan_cover, "The Book of Sadness is a poem written by saint Gregory of Narek in 1002. The poem consists of over eleven thousand lines of prayer. It was\n" +
                "originally written in Ancient Armenian or Grabar. It has been translated to several languages, including English, Russian and Farsi."));
        list.add(new DynamicRVModel("The Book Thief", "Markus Zusak", "552", R.drawable.book_thief_cover, "The Book Thief tells the story of Liesel, a little girl who is taken to a new home because her mother cannot afford\n" +
                "to take care of her. The story is told by Death, who becomes a character you come to respect and even feel sorry for by the end."));
        list.add(new DynamicRVModel("The Fault In Our Stars", "John Green", "313", R.drawable.fault_in_our_stars_cover, "Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her\n" +
                "final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group,\n" +
                "Hazels story is about to be completely rewritten."));
        list.add(new DynamicRVModel("The Great Gatsby", "F. Scott Fitzgerald", "208", R.drawable.gatsby_cover, "The story is narrated by a World War I veteran Nick Carraway, who, after moving to Long Island becomes the neighbor of some\n" +
                "millionaire named Jay Gatsby. The story tells us about Gatsby, his past, his love towards Daisy Buchanan, who had reluctantly got married to\n" +
                "Tom Buchanan while Jay fought in the First World War. The book gives the reader details of Jay and Daisys love, Toms affair and how it\n" +
                "affected Gatsbys life."));
        list.add(new DynamicRVModel("The Hound of the Baskervilles", "Arthur Conan Doyle", "174", R.drawable.the_hound_cover, "The Hound of the Baskervilles opens with a mini mystery—Sherlock Holmes and Dr. Watson speculate on the identity of the owner of a cane\n" +
                "that has been left in their office by an unknown visitor. Wowing Watson with his fabulous powers of observation, Holmes predicts the appearance of James\n" +
                "Mortimer, owner of the found object and a convenient entrée into the baffling curse of the Baskervilles."));
        list.add(new DynamicRVModel("The Hunger Games", "Suzanne Collins", "384", R.drawable.hunger_games_cover, "In what was once North America, the Capitol of Panem maintains its hold on its 12 districts by forcing them\n" +
                "each to select a boy and a girl, called Tributes, to compete in a nationally televised event called the Hunger Games. Every\n" +
                "citizen must watch as the youths fight to the death until only one remains. District 12 Tribute Katniss Everdeen has little\n" +
                "to rely on, other than her hunting skills and sharp instincts, in an arena where she must weigh survival against love."));
        list.add(new DynamicRVModel("The Sun and Her Flowers", "Rapi Kaur", "256", R.drawable.the_sun_and_her_flowers_cover, "A vibrant and transcendent journey about growth and healing. Ancestry and honoring ones roots. Expatriation and rising up to find\n" +
                "a home within yourself. Divided into five chapters and illustrated by kaur, the sun and her flowers is a journey of wilting, falling, rooting,\n" +
                "rising, and blooming."));
        list.add(new DynamicRVModel("Vardanank", "Derenik Demirchian", "918", R.drawable.vardanank_cover, "This historical novel tells the story of the Armenian-Persian war in 451, which is as well knwon as the War of Vardans or the Vardanank War.\n" +
                "It tells the story of the first war for Christianity taking place on the soil of the first Christian state. The novel describes the struggle of keeping the\n" +
                "faith that the Armenians went through."));
        list.add(new DynamicRVModel("1984", "George Orwell", "328", R.drawable.nineteen_eighty_four_cover, "In George Orwells 1984, Winston Smith wrestles with oppression in Oceania, a place where the Party scrutinizes human actions with\n" +
                "ever-watchful Big Brother. Defying a ban on individuality, Winston dares to express his thoughts in a diary and pursues a relationship with Julia."));

        return list;
    }
    */

}
