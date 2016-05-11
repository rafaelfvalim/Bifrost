package br.octa.thread.services;

import java.util.LinkedList;
import org.apache.log4j.Logger;
import br.octa.utils.ConfigUtils;

/**
 * Work queue para execução de atividades em um thread pool.
 * Classe n�o implementada no projeto Bifrost
 */
public class WorkQueue
{
	final static Logger logger = Logger.getLogger(WorkQueue.class);

    private int nThreads; /**< Número de threads no pool */
    private PoolWorker[] threads; /**< Lista de worker threads no pool */
    private LinkedList<Runnable> queue; /**< Fila de trabalho (com instâncias da RetornoSAP) */

    private final int DEFAULT_NUMBER_OF_THREADS = 10;

    /**
     * Construtor - Inicializa o thread pool.
     * @param nThreads Número de threads no pool
     */
    public WorkQueue(String name)
    {
        this.nThreads = getNumberOfThreads(name);
        queue = new LinkedList<Runnable>();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker(name);
            threads[i].start();
        }
    }

    /**
     * Agenda uma atividade para execução (insere uma atividade na fila).
     * @param r Instância para ser executada
     */
    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    /**
     * Classe de worker thread.
     */
    private class PoolWorker extends Thread {
    	private String name; /**< Nome do work queue */

        /**
         * Construtor - Inicializa a worker thread.
         * @param name Nome do work queue (para log)
		 */
    	public PoolWorker(String name) {
    		this.name = name;
    	}

		/**
    	 * Método de execução da worker thread (thread entry-point).
    	 * 
    	 * Neste método a thread fica eternamente esperando ítens disponiveis
    	 * na fila de atividades e processando-as.
    	 */
        public void run() {
            Runnable r;

            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        { }
                    }

                    r = (Runnable) queue.removeFirst();
                }

                // If we don't catch RuntimeException, 
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                	logger.error("Erro em uma worker thread do WorkQueue " + this.name + ": " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Traz o número de worker threads.
     * Se o valor não estiver no arquivo de configuração (propriedade "portal.workqueue.{NAME}.threads"),
     * o valor padrão (10) é retornado.
     * @param name O nome do work queue
     * @return Número de threads
     */
	private int getNumberOfThreads(String name){
		try{
			return Integer.parseInt(ConfigUtils.getThreads("workqueue." + name + ".threads"));
		}catch(Exception e){
			return DEFAULT_NUMBER_OF_THREADS;
		}
	}

}