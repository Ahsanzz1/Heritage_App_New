package uk.ac.bucks.heritage_app_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class ContentActivity extends AppCompatActivity {

    public ImageView IvCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // to remove the titlebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, // to initialize the activity in full screen mode
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_content);
        IvCon = (ImageView)findViewById(R.id.IvCon); // Image view to show the images of locations
        final ToggleButton tbPlay = (ToggleButton) findViewById(R.id.toggleButton); // button to play the audio clip

        // sharepreferences to store get the value of int which will be used to check which marker is tapped
        SharedPreferences sharedPref = getSharedPreferences("hscoreinfo", Context.MODE_PRIVATE);

        // get the int from shared preferences
        int S3 = sharedPref.getInt("score",0);

        //Textviews for the title and description of the places
        TextView score = (TextView) findViewById(R.id.text);
        TextView tvDetails = (TextView) findViewById(R.id.TvDetails);

        //make the playbutton invisible
        tbPlay.setVisibility(View.INVISIBLE);


        //if the int is equal to 1 show the information for library
        if (S3 == 1){
            score.setText("Library"); //set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.library)); // set the image
            //set the description
            tvDetails.setText("The tourist information centre located in the Eden centre near the bus station gives you information about places around, and were you can stay, you can even book local and national express bus travel. There are many other services the tourist centre gives which are accommodation, box office, travel, walking and cycling, guided tours, you can purchase national theatre tokens, and local events. You can request a newsletter or you can even be added to the mailing list which shows you what’s on.");

        }

        //if the int is equal to 2 show the information for Hen and chicken
        if (S3 == 2){
            score.setText("Hen & Chicken"); // set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.hen));//set the image
            //set the description
            tvDetails.setText("Hello, I am James George Peace. I am an apprentice at Lewis’ Silk Market (which was a forerunner of John Lewis Partnership). At age 22 I decided to look for fame and fortune in High Wycombe. My bolts of cloth could be purchased from street markets held in Wycombe Town centre.\n" +
                    "\n" +
                    "‘Peace Jones LTD’ stayed in business by marketing the uniforms for the Royal Artillery, who were stationed in town, and made the breeches for the Bucks Yeomanry. The British dyes came from Germany before the war. For a time, khaki was replaced by ‘Kitchener blue’ because, in 1914, the British army ran out of khaki. The ‘Hen & Chickens’ is the tailor’s mark, and the name ‘Peace’ is still written on the side gable.");
        }

        //if the int is equal to 3 show the information for the memorial
        if (S3 == 3){
            score.setText("War Memorial");// set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.war));//set the image
            //set the description
            tvDetails.setText("Hello, I am Fredrick Youens. This is the war memorial. After the war, families needed a focal point at home to remember those who had fallen. Memorials like this were erected and ceremonies are held every year to remember the dead. I, Fredrick Youens, am included on this memorial as I was in the British armed forces. I earned the highest and most prestigious award for gallantry (the Victoria Cross).\n" +
                    "\n" +
                    "On 7th July 1917, near Hill 60 in Belgium, I was wounded whilst out on patrol so I had to return to my trench to get my wound dressed. While this was happening, a report came in that the enemy were planning to raid our trenches. Even though I was wounded, I set out straight away and rallied the team of a Lewis Gun. During this process an enemy’s bomb fell on the Lewis Gun position without exploding. I immediately picked it up and hurled it over the parapet. Another bomb fell near the same place; so, I picked it up again with the intention of throwing it away, but it exploded in my hand and I got severely wounded. I managed to save several of my men’s lives but unfortunately I died later from my wounds.");
        }

        //if the int is equal to 4 show the information for the window
        if (S3 == 4){
            score.setText("Guildhall Stained Glass window");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.window));//set the image
            //set the description
            tvDetails.setText("This Window was created to celebrate the soldiers that returned from the war alive. It is placed up here on the town hall as this is the central location in the town.");
        }

        //if the int is equal to 5 show the information for view from the hill
        if (S3 == 5){
            score.setText("View from Tom Burt’s Hill");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.view));//set the image
            //set the description
            tvDetails.setText("In 1914, this would have been a military camp. Looking at this location from the top of Tom Burt’s hill, you would not see the Hospital; instead, you would view the base for the military camps. This is where us soldiers would sleep and train for the war.");
        }

        //if the int is equal to 6 show the wycombe abbey
        if (S3 == 6){
            score.setText("Wycombe Abbey");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.abbey));//set the image
            //set the description
            tvDetails.setText("Wycombe Abbey School was originally home to the Carrington family. Their son, Viscount Wendover, fought in World War 1 and is remembered in the Church memorial. Also, lots of fundraising events would have taken place in the grounds; these included concerts from the school girls.");
        }

        //if the int is equal to 7 show the war office
        if (S3 == 7){
            score.setText("War Office Railings");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.railing));//set the image
            //set the description
            tvDetails.setText("Hello, I am Lord Carrington 3rd and our family bought the 18th and 19th century gates and railings in 1908 from the War Office. These gates were the gates to the war office railings, which is where soldiers would sign up to fight during the war. They were first erected locally in High Wycombe in 1909 serving as the entrance to the Carrington residence at Daws Hill House.\n" +
                    "\n" +
                    "In 1924, the gates were moved 25 yards to the other side of the Dyke when I, Lord Carrington, gave the dyke and the path around it to the town as a memorial for my Son -Viscount Wendover – who died fighting during the First World War.\n" +
                    "\n" +
                    "Wycombe Abbey School became the owner of the gates and railings in 1929 when they bought Daws hill and the surrounding land. The gates were melted in WW2 for the spitfire fund and were replaced by wooden doors.\n" +
                    "\n" +
                    "The railings remain to this day, and their total length is 50m. In 2015 new ‘War Office’ gates replaced the old wooden doors, and were officially opened by my grandnephew Lord Carrington 6th.");

            tbPlay.setVisibility(View.VISIBLE);// make audio button visible

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.railing); //asign the audio clip to the media player
            if(tbPlay.isChecked())
                mp.start();
            tbPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!tbPlay.isChecked()){//if the toogle button is off
                        mp.pause();
                    }
                    else {//if the button is on
                        mp.start();//play the audio
                    }
                }

            });
        }

        //if the int is equal to 8 show the factory
        if (S3 == 8){
            score.setText("Looking Across The Rye Towards The Houses");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.factory));//set the image
            //set the description
            tvDetails.setText("Look across the Rye and take notice of the houses. Before World War 1, these houses would have been factories which used to produce chairs and other furniture. When World War 1 began, they were needed to make aircraft parts for different planes instead. Other houses also became the temporary schools for Wycombe High School (e.g. Bucks CC Area Office).");
        }

        //if the int is equal to 9 show the information for the school
        if (S3 == 9){
            score.setText("Boys Grammar School");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.grammer));//set the image
            //set the description
            tvDetails.setText("In 1915, the Royal Grammar School moved to a new building on Amersham hill and it was suggested that the school moved into a now vacated building on Easton Street. Unfortunately, the outbreak of the First World War meant that Wycombe High School’s\n" +
                    "\n" +
                    "buildings were needed for a hospital, so the girls moved into Easton Street and the boys had to wait.");
        }

        //if the int is equal to 10 show the information for station and recruitment
        if (S3 == 10){
            score.setText("Station & Recruitment Office");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.station));//set the image
            //set the description
            tvDetails.setText("The Bootlegger Pub used to be a recruitment office. Volunteer Defence Corps set it up for men that were too old for active service or involved in exempted occupations (e.g. craftsmen). The Fourth battalion centered in High Wycombe was raised. They were given army uniform, rifles, trained by military, and were called upon to guard enemy prisoners of war undertaking civilian work on farms and roadways. Lord Lincolnshire was instrumental in the establishment of Volunteer Defence Corps nationally, as well as in Buckinghamshire.");
        }

        //if the int is equal to 11 show the information for the boarding house
        if (S3 == 11){
            score.setText("Mary Christie’s Boarding House");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.marie));//set the image
            //set the description
            tvDetails.setText("Hello there, I am Mary Christie, and I was the first head teacher of Wycombe High School. During the First World War, girls came to my school from far distances. I suffered servilely from asthma and during the war, the location of the school had to be changed three times, meaning it had a bad effect on my health during the cold winter months. I, Mary Christie took over the Christ Church Vicarage as a hostel for myself and the girls to live in. I thought it was very important that the hostel was set up so that more girls could receive a good school education. In 1917, due to my bad asthma, I died of an attack. The students and many citizens from the area attended my funeral as I was a well-known figure. There is now a prize which can be awarded to women, named after me called the Mary Christie Prize.");

            tbPlay.setVisibility(View.VISIBLE);//make audio button visible

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.mary);//assign the audio clip to media player
            if(tbPlay.isChecked()) //is the button is checked
                mp.start(); // play the audio clip
            tbPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!tbPlay.isChecked()){//if the button is off
                        mp.pause();//pause the audio file
                    }
                    else {
                        mp.start(); //else play the audio file
                    }
                }

            });
        }

        //if the int is equal to 12 show the information for museum
        if (S3 == 12){
            score.setText("Museum"); // set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.museum));//set the image
            //set the description
            tvDetails.setText("Wycombe Museum (aka Wycombe Local History and Chair Museum) is a free local museum located in the town of High Wycombe, Buckinghamshire, England. It is run by Wycombe Heritage and Arts Trust, as of 1 December 2016. It was previously run by Wycombe District Council.\n" +
                    "The museum is located in Castle Hill House on Priory Avenue. It is situated in an 18th-century house on a medieval site, and surrounding the museum are Victorian gardens. The museum presents exhibitions the history of the local area, including the furniture industry, especially chair-making. There are also displays of Windsor chairs, lace, art and natural history.\n");
        }

        //if the int is equal to 13 show the information for cementery
        if (S3 == 13){
            score.setText("Cementery");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.cem));//set the image
            //set the description
            tvDetails.setText("I was a member of the cadets and when old enough, I joined the Wycombe Territorials. Immediately after the outbreak of the war in August 1914, I re-joined my old regiment, the Oxfordshire and Buckinghamshire Light Infantry, an infantry regiment of the British Army.\n" +
                    "\n" +
                    "When we first arrived in Europe we were sent to a place in Belgium and it was here that we first were in trenches and engaging with the enemy.\n" +
                    "\n" +
                    "In May-June 1916, we moved south again to prepare for the big push in the Somme. On 15th August, we were in the attack. Our objective was the next village, which was up a small hill, called Pozieres, and we were fighting with the Australians. It was very difficult and the Buckinghamshire Regiment had around 180 casualties. Unfortunately, I was one of them; I was shot straight through the spine. Whilst being put onto a stretcher and taken to a field hospital; I couldn’t feel or move my legs. On 20th August, I arrived back in England and was admitted to King George’s Hospital in London. On examination, it seemed my spine had been cut by the bullet and I had permanently lost the use of my legs.\n" +
                    "\n" +
                    "In a dreadful condition, I spent the last two years of my life in that hospital. At only 28 years old, I died from my wounds on 25th January 1918. All my family, except my mum of course, had served their country by fighting. My four brothers fought in the war, and two of them were also wounded, but survived the war. There were various wreaths left on my grave after the service and the one I’ll remember the most was from my fiancée, “In ever loving remembrance, to my darling, from your ever broken-hearted Vi.”");
        }

        //if the int is equal to 14 show the information for VAD
        if (S3 == 14){
            score.setText("The VAD Hospital Site");//set the title
            IvCon.setImageDrawable(getResources().getDrawable(R.drawable.vad));//set the image
            //set the description
            tvDetails.setText("In 1907, Voluntary Aid Detachments set up to fill gaps in Territorial medical services. Men and women volunteers learnt how to bandage, dress wounds, invalid cooking and hygiene. VAD nurses would also clean, wash patients, and prepare food. In September 1914, temporary hospitals were set up in church halls, public buildings and private houses. A VAD");

            tbPlay.setVisibility(View.VISIBLE);//make audio button visible

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.vad);//assign the audio file to media player
            if(tbPlay.isChecked())//if the button is on
                mp.start();//play the file
            tbPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!tbPlay.isChecked()){//if the button is off
                        mp.pause();//pause the audio file
                    }
                    else {// else play the file
                        mp.start();
                    }
                }

            });
        }

    }

}
