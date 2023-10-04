import synthesizer.GuitarString;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
//        /* create two guitar strings, for concert A and C */
//        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
//        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
//
//        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
//
//        while (true) {
//
//            /* check if the user has typed a key; if so, process it */
//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                double hz = CONCERT_A * Math.pow(2, (double) (keyboard.indexOf(key) + 1 - 24) / 12);
//                GuitarString guitarString = new GuitarString(hz);
//                guitarString.sample();
//            }
//
//            /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();
//
//            /* play the sample on standard audio */
//            StdAudio.play(sample);
//
//            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringC.tic();
        }
    }
}

