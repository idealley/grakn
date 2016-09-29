/*
 * MindmapsDB - A Distributed Semantic Database
 * Copyright (C) 2016  Mindmaps Research Ltd
 *
 * MindmapsDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MindmapsDB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MindmapsDB. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package io.mindmaps.factory;

import io.mindmaps.MindmapsComputer;
import io.mindmaps.MindmapsGraph;
import io.mindmaps.MindmapsGraphFactory;
import io.mindmaps.util.ErrorMessage;

/**
 * A client for creating a mindmaps graph from a running engine.
 * This is to abstract away factories and the backend from the user.
 * The deployer of engine decides on the backend and this class will handle producing the correct graphs.
 */
public class MindmapsGraphFactoryInMemory implements MindmapsGraphFactory {
    private final MindmapsTinkerInternalFactory factory;

    public MindmapsGraphFactoryInMemory(String keyspace){
        factory = new MindmapsTinkerInternalFactory(keyspace, null, null);
    }

    @Override
    public MindmapsGraph getGraph() {
        return factory.getGraph(false);
    }

    @Override
    public MindmapsGraph getGraphBatchLoading() {
        return factory.getGraph(true);
    }

    @Override
    public MindmapsComputer getGraphComputer() {
        throw new UnsupportedOperationException(ErrorMessage.UNSUPPORTED_GRAPH.getMessage("in-memory", "graph computer"));
    }
}