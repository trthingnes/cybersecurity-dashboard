// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getHomeassistantCoreInfo } from "../requests/services.gen";
import * as Common from "./common";
export const ensureUseGetHomeassistantCoreInfoData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetHomeassistantCoreInfoKeyFn(clientOptions), queryFn: () => getHomeassistantCoreInfo({ ...clientOptions }).then(response => response.data) });
