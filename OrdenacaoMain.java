import java.util.Random;

public class OrdenacaoMain {

    // para alterar o numero de array de forma livre e fácil 
    public final static int TAMANHO_ARRAY = 128;

    public static void main(String[] args) {
        
        int[] arrei = new int [TAMANHO_ARRAY];

        gerarArrayRandom(arrei);

        long tempoInicial = System.nanoTime();

        quickSort(arrei, 0, TAMANHO_ARRAY - 1);
        exibirArray(arrei);

        System.out.println("O método foi executado em " + (System.nanoTime() - tempoInicial));

    }

    /*
     * ---------------------------------------------------------------------------------------
     * MÉTODOS PARA GERAR ARRAYS: CRECENTE, DECRESCNTE E ALEATORIO
     * ---------------------------------------------------------------------------------------
     */

    // metodo para exibir arrays
    private static void exibirArray(int[] arrei) {
        for (int i : arrei) {
            System.out.print(arrei[i] + " ");
        }
        System.out.println();
    }

    //metodo para fazer arrays em ordem crescente
    private static void gerarArrayCrescente(int[] arrei) {
        for (int i = 0; i < TAMANHO_ARRAY; i++) {
            arrei[i] = i;
            System.out.print(arrei[i] + " ");
        }
        System.out.println();
    }

    //metodo para fazer arrays em ordem decrescente
    private static void gerarArrayDecrescente(int[] arrei) {
        AjudaArrayDecrescente(arrei);
        for (int i = TAMANHO_ARRAY - 1; i >= 0;) {
            i = arrei[i];
            arrei[i] = i;
            System.out.print(arrei[i] + " ");
            i--;
        }
        System.out.println();
    }

    //metodo para auxiliar o array em ordem decrescente
    private static void AjudaArrayDecrescente(int[] arrei) {
        for (int i = 0; i < TAMANHO_ARRAY; i++) {
            arrei[i] = i;
        }
    }

    //metodo para fazer arrays em ordem crescente de forma aleatória
    private static void gerarArrayRandom(int[] arrei) {
        Random gerador = new Random();
        for (int i = 0; i < TAMANHO_ARRAY; i++) {
            arrei[i] = gerador.nextInt(TAMANHO_ARRAY);
            System.out.print(arrei[i] + " ");
        }
        System.out.println();
    }

    // metodo para fazer arrays em ordem crescente de forma aleatória sem repetição  
    private static void gerarArrayRandomNoRepetition(int[] arrei) {
        Random gerador = new Random();
        boolean[] usado = new boolean[TAMANHO_ARRAY]; 
        for (int i = 0; i < TAMANHO_ARRAY; i++) {
            int num;
            do {
                num = gerador.nextInt(TAMANHO_ARRAY);
            } while (usado[num]);

            System.out.print(num + " ");

            arrei[i] = num;
            usado[num] = true; 
        }
        System.out.println();
    }

    /*
     * -----------------------------------------------------------------------------
     * MÉTODOS DE ORDENAÇÃO: 
     * -----------------------------------------------------------------------------
     */

    //metodo de ordenação BubbleSort
    private static void bubbleSort(int[] arrei) { 
        int n = TAMANHO_ARRAY;  
        int temp = 0;  
            for(int i=0; i < n; i++) {  
                for(int j=1; j < (n-i); j++) {  
                    if(arrei[j-1] > arrei[j]) {  
                    temp = arrei[j-1];  
                    arrei[j-1] = arrei[j];  
                    arrei[j] = temp;  
                    }  
                } 
            }
            exibirArray(arrei); 
        }

    //metodo de ordenação InsertSort
    public static void insertSort(int[] arrei) {
        int j;
        int key;
        int i;
    
        for (j = 1; j < arrei.length; j++) {
          key = arrei[j];
          for (i = j-1; (i>=0) && (arrei[i] > key); i--) {
             arrei[i+1] = arrei[i];
            }
            arrei[i+1] = key;
        }
        exibirArray(arrei);
    }

    //metodo de ordenação SelectionSort
    public static void selectionSort(int[] arrei) {
        for(int i = 0; i < arrei.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j < arrei.length; j++) {
				if(arrei[min] > arrei[j]) {
					min = j;
				}
			}
			
			int temp = arrei[i];
			arrei[i] = arrei[min];
			arrei[min] = temp;
		}
        exibirArray(arrei);
    }

    //metodo de ordenação HeapSort
    public static void heapSort(int[] arrei) {
        int n = arrei.length;
        // Construir o heap (reorganizar o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arrei, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Mover a raiz atual para o final
            int temp = arrei[0];
            arrei[0] = arrei[i];
            arrei[i] = temp;

            // Chamar heapify no heap reduzido
            heapify(arrei, i, 0);
        }
        exibirArray(arrei);
    
    }
    // metodo auxiliar para o HeapSort
    private static void heapify(int[] arrei, int n, int i) {
        int largest = i;  // Inicializar largest como raiz
        int left = 2 * i + 1;  // filho esquerdo
        int right = 2 * i + 2;  // filho direito

        // Se o filho esquerdo é maior que a raiz
        if (left < n && arrei[left] > arrei[largest]) {
            largest = left;
        }

        // Se o filho direito é maior que o maior até agora
        if (right < n && arrei[right] > arrei[largest]) {
            largest = right;
        }

        // Se o maior não é a raiz
        if (largest != i) {
            int swap = arrei[i];
            arrei[i] = arrei[largest];
            arrei[largest] = swap;

            // Recursivamente heapify a subárvore afetada
            heapify(arrei, n, largest);
        }
    }

    //metodo de ordenação ShellSort
    public static void shellSort(int[] arrei) {
        int n = arrei.length;

        // Começa com um grande intervalo, depois reduz o intervalo
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Executa a ordenação por inserção para este intervalo
            for (int i = gap; i < n; i++) {
                // Guarda o valor a ser inserido
                int temp = arrei[i];

                // Move os elementos de arr[0..i-gap] que são maiores que temp
                // uma posição à frente de sua posição atual
                int j;
                for (j = i; j >= gap && arrei[j - gap] > temp; j -= gap) {
                    arrei[j] = arrei[j - gap];
                }

                // Coloca temp na sua posição correta
                arrei[j] = temp;
            }
        }
        exibirArray(arrei);
    }

    //metodo de ordenação MergeSort
    private static void mergeSort(int[] arrei) {
		
		int length = arrei.length;
		if (length <= 1) return; //base case
		
		int middle = length / 2;
		int[] leftArray = new int[middle];
		int[] rightArray = new int[length - middle];
		
		int i = 0; //left array
		int j = 0; //right array
		
		for(; i < length; i++) {
			if(i < middle) {
				leftArray[i] = arrei[i];
			}
			else {
				rightArray[j] = arrei[i];
				j++;
			}
		}
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, arrei);
	}
	//metodo auxiliar para o MergeSort
	private static void merge(int[] leftArray, int[] rightArray, int[] array) {
		
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0; //indices
		
		//check the conditions for merging
		while(l < leftSize && r < rightSize) {
			if(leftArray[l] < rightArray[r]) {
				array[i] = leftArray[l];
				i++;
				l++;
			}
			else {
				array[i] = rightArray[r];
				i++;
				r++;
			}
		}
		while(l < leftSize) {
			array[i] = leftArray[l];
			i++;
			l++;
		}
		while(r < rightSize) {
			array[i] = rightArray[r];
			i++;
			r++;
		}
	}

    //metodo de ordenação QuicKSort
    private static void quickSort(int[] arrei, int start, int end) {
		
		if(end <= start) return; //base case
		
		int pivot = partition(arrei, start, end);
		quickSort(arrei, start, pivot - 1);
		quickSort(arrei, pivot + 1, end);	
	}
    //metodo auxiliar para o QuickSort
	private static int partition(int[] arrei, int start, int end) {
		
		int pivot = arrei[end];
		int i = start - 1;
		
		for(int j = start; j <= end; j++) {
			if(arrei[j] < pivot) {
				i++;
				int temp = arrei[i];
				arrei[i] = arrei[j];
				arrei[j] = temp;
			}
		}
		i++;
		int temp = arrei[i];
		arrei[i] = arrei[end];
		arrei[end] = temp;
		
		return i;
	}

}