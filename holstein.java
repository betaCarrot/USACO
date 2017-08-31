/*
 ID: majesti2
 LANG: JAVA
 TASK: holstein
 */

import java.util.*;
import java.io.*;

public class holstein {
	private static int[] required;
	private static int V;
	private static int G;
	private static int[][] available;
	private static PrintWriter out;
	

	public static void main (String [] args) throws IOException {
		int count = 0;
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		V= Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		required = new int[V];
		for(int i=0;i<V;i++){
			required[i] = Integer.parseInt(st1.nextToken());
		}
		StringTokenizer st2 = new StringTokenizer(f.readLine());
		G = Integer.parseInt(st2.nextToken());
		available = new int[G][V];
		for(int i=0;i<G;i++){
			StringTokenizer st3 = new StringTokenizer(f.readLine());
			for(int k=0;k<V;k++){
				available[i][k] = Integer.parseInt(st3.nextToken());
			}
		}
		search();
		out.close();
	}

	public static void search(){
		for(int count=1;count<=G;count++){
			//out.println(count);
			if(count==15){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
														for(int k=j+1;k<G;k++){
															for(int l=k+1;l<G;l++){
																for(int m=l+1;l<G;m++){
																	for(int n=m+1;n<G;n++){
																		for(int o=n+1;o<G;o++){								int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z]+available[k][z]+available[l][z]+available[m][z]+available[n][z]+available[o][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1)+" "+(n+1)+" "+(o+1));
					return;
				}
				
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==14){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
														for(int k=j+1;k<G;k++){
															for(int l=k+1;l<G;l++){
																for(int m=l+1;m<G;m++){
																	for(int n=m+1;n<G;n++){									int counter = 0;
				//out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1)+" "+(n+1));
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z]+available[k][z]+available[l][z]+available[m][z]+available[n][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1)+" "+(n+1));
					return;
				}

																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==13){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
														for(int k=j+1;k<G;k++){
															for(int l=k+1;l<G;l++){
																for(int m=l+1;m<G;m++){										int counter = 0;
				//out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1));
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z]+available[k][z]+available[l][z]+available[m][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1));
					return;
				}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==12){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
														for(int k=j+1;k<G;k++){
															for(int l=k+1;l<G;l++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z]+available[k][z]+available[l][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1));
					return;
				}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==11){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
														for(int k=j+1;k<G;k++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z]+available[k][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1)+" "+(k+1));
					return;
				}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==10){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
													for(int j=i+1;j<G;j++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z]+available[j][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1)+" "+(j+1));
					return;
				}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==9){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
												for(int i=h+1;i<G;i++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z]+available[i][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1)+" "+(i+1));
					return;
				}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==8){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
											for(int h=g+1;h<G;h++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z]+available[h][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1)+" "+(h+1));
					return;
				}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==7){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
										for(int g=f+1;g<G;g++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z]+available[g][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1)+" "+(g+1));
					return;
				}
										}
									}
								}
							}
						}
					}
				}
			}

			if(count==6){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
									for(int f=e+1;f<G;f++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z]+available[f][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1)+" "+(f+1));
					return;
				}
									}
								}
							}
						}
					}
				}
			}

			if(count==5){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
								for(int e=d+1;e<G;e++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z]+available[e][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1)+" "+(e+1));
					return;
				}
								}
							}
						}
					}
				}
			}

			if(count==4){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
							for(int d=c+1;d<G;d++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z]+available[d][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1));
					return;
				}
							}
						}
					}
				}
			}

			if(count==3){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
						for(int c=b+1;c<G;c++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z]+available[c][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1)+" "+(c+1));
					return;
				}
						}
					}
				}
			}

			
			if(count==2){
				for(int a=0;a<G;a++){
					for(int b=a+1;b<G;b++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=(available[a][z]+available[b][z])) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1)+" "+(b+1));
					return;
				}
					}
				}
			}

			if(count==1){
				for(int a=0;a<G;a++){
				int counter = 0;
				for(int z=0;z<V;z++){
					if(required[z]<=available[a][z]) counter++;}
				if(counter==V){
					out.println(count+" "+(a+1));
					return;
				}
				}
			}
		}
	}
}


	
	
