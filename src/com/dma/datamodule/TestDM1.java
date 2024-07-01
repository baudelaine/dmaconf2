package com.dma.datamodule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dma.web.QuerySubject;
import com.dma.web.Relation;
import com.dma.web.Seq;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestDM1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Charset charset = StandardCharsets.UTF_8;
		Path dmaMdl = Paths.get("/home/fr054721/Documents/datacc/latestDM/dmamdl3.json");
		Path cogDM = Paths.get("/home/fr054721/Documents/datacc/latestDM/DataModule.json");
		Path output = Paths.get("/home/fr054721/Documents/datacc/latestDM/DataModule-from-pojo.json");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(Include.NON_NULL);
		
		DM dm = null;
		Map<String, List<QuerySubject>> model = null;
		List<QuerySubject> qsList = null;
		
		if(Files.exists(cogDM)) {
			dm = mapper.readValue(cogDM.toFile(), DM.class);
			for(QuerySubjectDM qs: dm.getQuerySubject()) {
				int fieldCount = qs.getItem().size();
				System.out.println(qs.getLabel() + " -> " + fieldCount);
				for(Map<String, QueryItemDM> map: qs.getItem()) {
					QueryItemDM qi = map.get("queryItem");
					System.out.println("\t" + qi.getIdentifier());
				}
			}
		}
		
		if(Files.exists(dmaMdl)) {
			model =  mapper.readValue(dmaMdl.toFile(), new TypeReference<Map<String, List<QuerySubject>>>(){});
			qsList = (List<QuerySubject>) model.get("querySubjects");
			
			Map<String, String> idForExpressions = new HashMap<String, String>();
			for(QuerySubject qs: qsList) {
				idForExpressions.put(qs.getTable_alias(), qs.getIdForExpression());
				idForExpressions.put(qs.getTable_name(), qs.getIdForExpression());
			}
			
			if(dm.getRelationship() == null) {
				dm.setRelationship(new ArrayList<RelationshipDM>());
			}
			
			for(QuerySubject qs: qsList) {
				for(Relation rel: qs.getRelations()) {
					if(rel.isFin() || rel.isRef() || rel.isSec() || rel.isTra()) {
						RelationshipDM relDM = new RelationshipDM();
						RelationshipLeftDM left = new RelationshipLeftDM();
						left.setRef(idForExpressions.get(qs.getTable_alias()));
						left.setMaxcard("one");
						left.setMincard("one");
						relDM.setLeft(left);
						RelationshipRightDM right = new RelationshipRightDM();
						right.setRef(idForExpressions.get(rel.getPktable_alias()));
						right.setMaxcard("many");
						right.setMincard("one");
						relDM.setRight(right);
						List<RelationshipLinkDM> links = new ArrayList<RelationshipLinkDM>(); 
						for(Seq seq: rel.getSeqs()) {
							RelationshipLinkDM link = new RelationshipLinkDM();
							link.setLeftRef(seq.getColumn_name());
							link.setRightRef(seq.getPkcolumn_name());
							link.setComparisonOperator("equalTo");
							links.add(link);
						}
						relDM.setLink(links);
						relDM.setJoinFilterType("none");
						relDM.setIdentifier(idForExpressions.get(qs.getTable_alias()) + "_" + idForExpressions.get(rel.getPktable_alias()));
						relDM.setLabel(idForExpressions.get(qs.getTable_alias()) + "<-->" + idForExpressions.get(rel.getPktable_alias()));
						relDM.setPropertyOverride(Arrays.asList("NEW"));
						relDM.setIdForExpression(idForExpressions.get(qs.getTable_alias()) + "_" + idForExpressions.get(rel.getPktable_alias()));
						dm.getRelationship().add(relDM);
					}
				}
			}
		}
		
		mapper.writeValue(output.toFile(), dm);
		
		
	}

}
