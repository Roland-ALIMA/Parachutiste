import java.util.Scanner;

class Parachutiste {

	public static void main(String[] args) {

		Scanner clavier = new Scanner(System.in);

		double masse = 80.0;
		do {
			System.out.print("masse du parachutiste (>= 40) ? ");
			masse = clavier.nextDouble();
		} while (masse < 40.0);

		double h0 = 39000.0;
		do {
			System.out.print("hauteur de depart du parachutiste (>= 250) ? ");
			h0 = clavier.nextDouble();
		} while (h0 < 250.0);

		/*******************************************
		 * Completez le programme a partir d'ici.
		 *******************************************/

		double g = 9.81;
		double v0 = 0.0;
		double t0 = 0.0;

		double vitesse = v0;
		double hauteur = h0;
		double accel = g;
		double t = t0;
		double surface = 2.0;

		int depasseVitesseSon = 0;
		int limiteHauteur = 0;
		int vitesseMax = 0;

		double s = surface/masse;
		double vitesseSon = 343;
		
		System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);

		while (hauteur > 0) {

			if (hauteur > 2500) {

				t++;
				double q = Math.exp(-s*(t - t0));
				vitesse = (g/s)*(1 - q) + v0*q;
				hauteur = h0 - (g/s)*(t - t0) - ((v0 - (g/s))/s)*(1 - q);
				accel = g - s*vitesse;

				if (vitesse > vitesseSon) {
					depasseVitesseSon++;
				}

				if (hauteur < 2500) {
					limiteHauteur++;
				}
				
				if (accel < 0.5) {
					vitesseMax++;
				}

				if (depasseVitesseSon == 1) {
					System.out.println("## Felix depasse la vitesse du son");
					System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);
				} else if (vitesseMax == 1) {
					System.out.println("## Felix a atteint sa vitesse maximale");
					System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);
				} else { 
					if (limiteHauteur == 1) {
						System.out.println("## Felix ouvre son parachute");
						v0 = vitesse;
						h0 = hauteur;
						t0 = t;
						surface = 25.0;
						s = surface/masse;
					}
					System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);
				}

			} else {

				t++;

				double q = Math.exp(-s*(t - t0));
				vitesse = (g/s)*(1 - q) + v0*q;
				hauteur = h0 - (g/s)*(t - t0) - ((v0 - (g/s))/s)*(1 - q);
				accel = g - s*vitesse;
				
				limiteHauteur++;
				
				if (limiteHauteur == 1) {
					System.out.println("## Felix ouvre son parachute");
					v0 = vitesse;
					h0 = hauteur;
					t0 = t;
					surface = 25.0;
					s = surface/masse;
				}

				if (accel < 0.5) {
					vitesseMax++;
				}

				if (hauteur > 0) {
					if (vitesseMax == 1) {
						System.out.println("## Felix a atteint sa vitesse maximale");
						System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);
					} else {
						System.out.printf("%.0f, %.4f, %.4f, %.5f\n", t, hauteur, vitesse, accel);
					}
				} else {
					if (vitesseMax == 1) {
						System.out.println("## Felix a atteint sa vitesse maximale");
					}
				}

			}
		}

		/*******************************************
		 * Ne rien modifier apres cette ligne.
		 *******************************************/
		clavier.close();
	}
}